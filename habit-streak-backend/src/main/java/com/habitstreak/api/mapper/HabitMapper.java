package com.habitstreak.api.mapper;

import com.habitstreak.api.dto.HabitRequestDTO;
import com.habitstreak.api.dto.HabitResponseDTO;
import com.habitstreak.api.model.Habit;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {

  public Habit toModel(HabitRequestDTO dto) {
    Habit habit = new Habit();
    habit.setName(dto.getName());
    habit.setTargetPerWeek(dto.getTargetPerWeek());
    return habit;
  }

  public HabitResponseDTO toResponse(Habit habit) {
    HabitResponseDTO dto = new HabitResponseDTO();
    dto.setName(habit.getName());
    dto.setTargetPerWeek(habit.getTargetPerWeek());
    dto.setCompletedDays(habit.getCompletedDays());
    dto.setCreatedAt(habit.getCreatedAt());
    dto.setStreak(habit.getStreak());
    return dto;
  }
}
