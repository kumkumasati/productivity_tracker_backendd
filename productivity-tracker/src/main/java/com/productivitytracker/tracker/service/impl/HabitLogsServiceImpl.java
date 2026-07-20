package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.HabitLogsDto;
import com.productivitytracker.tracker.entity.HabitLogs;
import com.productivitytracker.tracker.entity.Habits;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.HabitLogsMapper;
import com.productivitytracker.tracker.repository.HabitLogsRepository;
import com.productivitytracker.tracker.repository.HabitsRepository;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.HabitLogsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HabitLogsServiceImpl implements HabitLogsService {

    private HabitLogsRepository habitLogsRepository;
    private HabitsRepository habitsRepository;
    private UserRepository userRepository;

    // ✅ CREATE
    @Override
    public HabitLogsDto create(HabitLogsDto dto) {

        Habits habit = habitsRepository.findById(dto.getHabitId())
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        HabitLogs log = HabitLogsMapper.mapToEntity(dto);

        log.setHabit(habit);
        log.setUser(user);

        return HabitLogsMapper.mapToDto(habitLogsRepository.save(log));
    }

    // ✅ GET BY ID
    @Override
    public HabitLogsDto getById(Long id) {
        HabitLogs log = habitLogsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        return HabitLogsMapper.mapToDto(log);
    }

    // ✅ GET ALL
    @Override
    public List<HabitLogsDto> getAll() {
        return habitLogsRepository.findAll()
                .stream()
                .map(HabitLogsMapper::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ UPDATE
    @Override
    public HabitLogsDto update(Long id, HabitLogsDto dto) {

        HabitLogs log = habitLogsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        if (dto.getDate() != null) {
            log.setDate(java.time.LocalDate.parse(dto.getDate()));
        }

        log.setCompleted(dto.getCompleted());
        log.setCount(dto.getCount());

        return HabitLogsMapper.mapToDto(habitLogsRepository.save(log));
    }

    // ✅ DELETE
    @Override
    public void delete(Long id) {
        HabitLogs log = habitLogsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));

        habitLogsRepository.delete(log);
    }
}