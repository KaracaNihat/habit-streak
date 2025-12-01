package com.habitstreak.api.service;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.repository.HabitRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HabitService {
  private final HabitRepository habitRepository;

  public List<Habit> getAllHabits() {
    return habitRepository.findAll();
  }

  public Optional<Habit> getHabitById(String id) {
    return habitRepository.findById(id);
  }

  public Habit createHabit(Habit habit) {
    return habitRepository.insert(habit);
  }

  public Optional<Habit> updateHabitName(String id, Habit newData) {
    return getHabitById(id)
        .map(
            existingHabit -> {
              existingHabit.setName(newData.getName());
              return habitRepository.save(existingHabit);
            });
  }

  public Boolean deleteHabitById(String id) {
    if (getHabitById(id).isPresent()) {
      habitRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
