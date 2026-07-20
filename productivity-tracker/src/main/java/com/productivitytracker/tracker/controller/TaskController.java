package com.productivitytracker.tracker.controller;

import com.productivitytracker.tracker.dto.TaskDto;
import com.productivitytracker.tracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/{userId}/tasks")
public class TaskController {

    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@PathVariable Long userId,
                                               @Valid @RequestBody TaskDto dto,
                                               Authentication authentication) {
        requireSelf(userId, authentication);
        return ResponseEntity.ok(taskService.createTask(userId, dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable Long userId,
                                                    Authentication authentication) {
        requireSelf(userId, authentication);
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long userId,
                                            @PathVariable Long taskId,
                                            Authentication authentication) {
        requireSelf(userId, authentication);
        return ResponseEntity.ok(taskService.getTaskById(taskId, currentUserId(authentication)));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long userId,
                                               @PathVariable Long taskId,
                                               @Valid @RequestBody TaskDto dto,
                                               Authentication authentication) {
        requireSelf(userId, authentication);
        return ResponseEntity.ok(taskService.updateTask(taskId, dto, currentUserId(authentication)));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long userId,
                                              @PathVariable Long taskId,
                                              Authentication authentication) {
        requireSelf(userId, authentication);
        taskService.deleteTask(taskId, currentUserId(authentication));
        return ResponseEntity.ok("Task deleted successfully!");
    }

    // The JwtAuthFilter puts the authenticated user's id in the Authentication principal.
    private Long currentUserId(Authentication authentication) {
        return (Long) authentication.getPrincipal();
    }

    // Belt-and-suspenders: the {userId} in the URL must match the authenticated caller.
    // Real per-record ownership is still enforced in TaskServiceImpl.
    private void requireSelf(Long pathUserId, Authentication authentication) {
        if (!pathUserId.equals(currentUserId(authentication))) {
            throw new AccessDeniedException("You do not have access to this user's tasks");
        }
    }
}
