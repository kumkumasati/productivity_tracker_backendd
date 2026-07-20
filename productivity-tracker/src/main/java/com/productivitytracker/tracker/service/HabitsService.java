
package com.productivitytracker.tracker.service;

import com.productivitytracker.tracker.dto.HabitsDto;

import java.util.List;

public interface HabitsService {

    HabitsDto createHabit(HabitsDto habitsDto);

    HabitsDto getHabitById(Long id);

    List<HabitsDto> getAllHabits();

    HabitsDto updateHabit(Long id, HabitsDto habitsDto);

    void deleteHabit(Long id);
}