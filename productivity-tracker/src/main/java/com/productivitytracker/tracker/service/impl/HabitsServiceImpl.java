package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.HabitsDto;
import com.productivitytracker.tracker.entity.Habits;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.HabitsMapper;
import com.productivitytracker.tracker.repository.HabitsRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.HabitsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HabitsServiceImpl implements HabitsService {

    private HabitsRepository habitsRepository;
    private UserRepository userRepository;

    // ✅ CREATE
    @Override
    public HabitsDto createHabit(HabitsDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        Habits habit = HabitsMapper.mapToHabits(dto);

        // override user with managed entity
        habit.setUser(user);

        Habits saved = habitsRepository.save(habit);

        return HabitsMapper.mapToHabitsDto(saved);
    }

    // ✅ GET BY ID
    @Override
    public HabitsDto getHabitById(Long id) {

        Habits habit = habitsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found with id: " + id));

        return HabitsMapper.mapToHabitsDto(habit);
    }

    // ✅ GET ALL
    @Override
    public List<HabitsDto> getAllHabits() {

        return habitsRepository.findAll()
                .stream()
                .map(HabitsMapper::mapToHabitsDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public HabitsDto updateHabit(Long id, HabitsDto dto) {

        Habits habit = habitsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found with id: " + id));

        // update fields
        habit.setHabitName(dto.getName());
        habit.setFrequency(dto.getFrequency());
        habit.setTargetCount(dto.getTargetCount());

        // update user if provided
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

            habit.setUser(user);
        }

        Habits updated = habitsRepository.save(habit);

        return HabitsMapper.mapToHabitsDto(updated);
    }

    // ✅ DELETE
    @Override
    public void deleteHabit(Long id) {

        Habits habit = habitsRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found with id: " + id));

        habitsRepository.delete(habit);
    }
}