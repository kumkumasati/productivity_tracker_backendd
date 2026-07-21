package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.TaskDto;
import com.productivitytracker.tracker.entity.Achievement;
import com.productivitytracker.tracker.entity.Task;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.entity.UserAchievement;
import com.productivitytracker.tracker.entity.UserStats;
import com.productivitytracker.tracker.entity.XpLog;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.TaskMapper;
import com.productivitytracker.tracker.repository.AchievementRepository;
import com.productivitytracker.tracker.repository.TaskRepository;
import com.productivitytracker.tracker.repository.UserAchievementRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.repository.UserStatsRepository;
import com.productivitytracker.tracker.repository.XpLogRepository;
import com.productivitytracker.tracker.service.TaskService;
import com.productivitytracker.tracker.entity.TaskLogs;
import com.productivitytracker.tracker.repository.TaskLogsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskLogsRepository taskLogsRepository;
    private AchievementRepository achievementRepository;
    private UserAchievementRepository userAchievementRepository;
    private UserStatsRepository userStatsRepository;
    private XpLogRepository xpLogRepository;

    // Must match the titles seeded by AchievementSeeder exactly.
    private static final Map<String, Integer> TASK_COUNT_ACHIEVEMENTS = new LinkedHashMap<>() {{
        put("First Steps", 1);
        put("Getting Things Done", 5);
        put("Task Master", 20);
    }};

    // XP awarded per completed task, based on its priority. Anything else
    // (null, unrecognised value) falls back to DEFAULT_TASK_XP.
    private static final Map<String, Integer> XP_BY_PRIORITY = Map.of(
            "HIGH", 15,
            "MEDIUM", 10,
            "LOW", 5
    );
    private static final int DEFAULT_TASK_XP = 10;

    @Override
    public TaskDto createTask(Long userId, TaskDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found " + userId));

        Task task = TaskMapper.mapToTask(dto);
        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        return TaskMapper.mapToTaskDto(savedTask);
    }

    @Override
    public TaskDto getTaskById(Long taskId, Long requestingUserId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found " + taskId));

        assertOwnership(task, requestingUserId);

        return TaskMapper.mapToTaskDto(task);
    }

    @Override
    public List<TaskDto> getTasksByUser(Long userId) {

        List<Task> tasks = taskRepository.findByUserUserId(userId);

        return tasks.stream()
                .map(TaskMapper::mapToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto dto, Long requestingUserId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found " + taskId));

        assertOwnership(task, requestingUserId);

        String previousStatus = task.getStatus();
        String newStatus = dto.getStatus();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(newStatus);
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        boolean justCompleted = "COMPLETED".equalsIgnoreCase(newStatus)
                && !"COMPLETED".equalsIgnoreCase(previousStatus);
        boolean justReopened = !"COMPLETED".equalsIgnoreCase(newStatus)
                && "COMPLETED".equalsIgnoreCase(previousStatus);

        if (justCompleted) {
            task.setCompletedDate(LocalDateTime.now());
        } else if (justReopened) {
            task.setCompletedDate(null);
        }

        Task updated = taskRepository.save(task);

        if (justCompleted) {
            logTaskAction(updated, "COMPLETED");
            rewardTaskCompletion(updated, requestingUserId);
            checkAndAwardTaskCountAchievements(requestingUserId);
        } else if (justReopened) {
            logTaskAction(updated, "REOPENED");
        }

        return TaskMapper.mapToTaskDto(updated);
    }

    private void logTaskAction(Task task, String action) {
        TaskLogs log = new TaskLogs();
        log.setTask(task);
        log.setUser(task.getUser());
        log.setAction(action);
        log.setCreatedAt(LocalDateTime.now());
        taskLogsRepository.save(log);
    }

    // Awards XP for completing this task "today" and updates the user's
    // streak: completing at least one task on a new calendar day extends
    // the streak by one; completing more tasks the same day still earns XP
    // but doesn't inflate the streak further; missing a day resets it to 1.
    private void rewardTaskCompletion(Task task, Long userId) {

        int xpAmount = XP_BY_PRIORITY.getOrDefault(
                task.getPriority() == null ? "" : task.getPriority().toUpperCase(),
                DEFAULT_TASK_XP
        );

        XpLog xpLog = new XpLog();
        xpLog.setUser(task.getUser());
        xpLog.setSource("TASK_COMPLETED");
        xpLog.setAmount(xpAmount);
        xpLogRepository.save(xpLog);

        UserStats stats = userStatsRepository.findByUser_UserId(userId)
                .orElseGet(() -> {
                    UserStats s = new UserStats();
                    s.setUser(task.getUser());
                    s.setTotalXp(0);
                    s.setCurrentStreak(0);
                    s.setLongestStreak(0);
                    return s;
                });

        stats.setTotalXp((stats.getTotalXp() == null ? 0 : stats.getTotalXp()) + xpAmount);

        LocalDate today = LocalDate.now();
        LocalDate lastActive = stats.getLastActivityDate();

        if (lastActive == null || lastActive.isBefore(today.minusDays(1))) {
            // First ever activity, or the streak was broken by a missed day.
            stats.setCurrentStreak(1);
        } else if (lastActive.equals(today.minusDays(1))) {
            // Completed something yesterday, and now today too — extend it.
            stats.setCurrentStreak((stats.getCurrentStreak() == null ? 0 : stats.getCurrentStreak()) + 1);
        }
        // else lastActive.equals(today): already counted today, streak unchanged.

        int currentStreak = stats.getCurrentStreak() == null ? 0 : stats.getCurrentStreak();
        int longestStreak = stats.getLongestStreak() == null ? 0 : stats.getLongestStreak();
        stats.setLongestStreak(Math.max(longestStreak, currentStreak));

        stats.setLastActivityDate(today);

        userStatsRepository.save(stats);
    }

    // Checks the user's total completed-task count against each threshold in
    // TASK_COUNT_ACHIEVEMENTS, and awards any newly-crossed achievement that
    // they don't already have. Safe to call every time a task is completed —
    // findByUser_UserIdAndAchievement_AchievementId prevents duplicate awards.
    private void checkAndAwardTaskCountAchievements(Long userId) {

        long completedCount = taskRepository.countByUserUserIdAndStatusIgnoreCase(userId, "COMPLETED");

        TASK_COUNT_ACHIEVEMENTS.forEach((title, threshold) -> {
            if (completedCount < threshold) {
                return;
            }

            achievementRepository.findByTitle(title).stream().findFirst().ifPresent(achievement -> {
                boolean alreadyEarned = userAchievementRepository
                        .findByUser_UserIdAndAchievement_AchievementId(userId, achievement.getAchievementId())
                        .isPresent();

                if (!alreadyEarned) {
                    awardAchievement(userId, achievement);
                }
            });
        });
    }

    private void awardAchievement(Long userId, Achievement achievement) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }

        UserAchievement ua = new UserAchievement();
        ua.setUser(user);
        ua.setAchievement(achievement);
        userAchievementRepository.save(ua);
    }

    @Override
    public void deleteTask(Long taskId, Long requestingUserId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found " + taskId));

        assertOwnership(task, requestingUserId);

        boolean hasHistory = !taskLogsRepository.findByTaskTaskId(taskId).isEmpty();
        if (hasHistory) {
            throw new com.productivitytracker.tracker.exception.TaskCannotBeDeletedException(
                    "This task has completion history that's part of your weekly report and can't be deleted.");
        }

        taskRepository.delete(task);
    }

    // Throws 403 if the authenticated caller does not own this task,
    // instead of silently letting anyone read/edit/delete anyone's data.
    private void assertOwnership(Task task, Long requestingUserId) {
        if (task.getUser() == null || !task.getUser().getUserId().equals(requestingUserId)) {
            throw new AccessDeniedException("You do not have access to this task");
        }
    }
}