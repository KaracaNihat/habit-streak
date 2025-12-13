package com.habitstreak.api.service;

import com.habitstreak.api.exception.AlreadyExistsException;
import com.habitstreak.api.exception.NotFoundException;
import com.habitstreak.api.model.Habit;
import com.habitstreak.api.repository.HabitRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HabitService {
  private final HabitRepository habitRepository;

  public List<Habit> getAllHabits(UUID userId) {
    return habitRepository.findAllByUserId(userId);
  }

  public Habit getHabitById(UUID id) {
    return habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit not found"));
  }

  public Habit createHabit(Habit habit, UUID userId) {
    if (habitRepository.findByName(habit.getName()).isPresent()) {
      throw new AlreadyExistsException("Habit with name already exists");
    }
    habit.setUserId(userId);
    return habitRepository.insert(habit);
  }

  public Habit updateHabit(UUID id, Habit newData) {
    Habit habit = getHabitById(id);
    habit.setName(newData.getName());
    habit.setTargetPerWeek(newData.getTargetPerWeek());
    habit.setCompletedDays(newData.getCompletedDays());
    habit.setStreak(newData.getStreak());
    return habitRepository.save(habit);
  }

  public void deleteHabitById(UUID id) {
    habitRepository.deleteById(getHabitById(id).getId());
  }
}
