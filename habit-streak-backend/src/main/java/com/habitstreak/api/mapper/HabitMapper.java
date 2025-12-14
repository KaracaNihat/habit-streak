package com.habitstreak.api.mapper;

import com.habitstreak.api.dto.habit.HabitRequestDTO;
import com.habitstreak.api.dto.habit.HabitResponseDTO;
import com.habitstreak.api.model.Habit;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {

  public Habit toModel(HabitRequestDTO dto) {
    Habit habit = new Habit();
    habit.setName(dto.getName());
    habit.setTargetPerWeek(dto.getTargetPerWeek());
    habit.setCompletedDays(dto.getCompletedDays());
    habit.setStreak(dto.getStreak());
    return habit;
  }

  public HabitResponseDTO toResponse(Habit habit) {
    HabitResponseDTO dto = new HabitResponseDTO();
    dto.setId(habit.getId());
    dto.setName(habit.getName());
    dto.setTargetPerWeek(habit.getTargetPerWeek());
    dto.setCompletedDays(habit.getCompletedDays());
    dto.setCreatedAt(habit.getCreatedAt());
    dto.setStreak(habit.getStreak());
    return dto;
  }
}
