package com.productivitytracker.tracker.config;

import com.productivitytracker.tracker.entity.Achievement;
import com.productivitytracker.tracker.repository.AchievementRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds the achievement catalog on startup, but only once — if the
 * achievements table already has rows, this does nothing. Safe to
 * redeploy repeatedly without creating duplicates.
 *
 * The exact titles here must match TaskServiceImpl.TASK_COUNT_ACHIEVEMENTS,
 * since that's how completed-task-count achievements get matched and
 * awarded to a user.
 */
@Component
@AllArgsConstructor
public class AchievementSeeder implements CommandLineRunner {

    private final AchievementRepository achievementRepository;

    @Override
    public void run(String... args) {
        if (achievementRepository.count() > 0) {
            return;
        }

        achievementRepository.saveAll(List.of(
                achievement("First Steps", "Complete your first quest.", 10),
                achievement("Getting Things Done", "Complete 5 quests.", 50),
                achievement("Task Master", "Complete 20 quests.", 100)
        ));
    }

    private Achievement achievement(String title, String description, int xpReward) {
        Achievement a = new Achievement();
        a.setTitle(title);
        a.setDescription(description);
        a.setXpReward(xpReward);
        return a;
    }
}