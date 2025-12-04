package com.habitstreak.api.service;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.repository.HabitRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HabitService {
  private final HabitRepository habitRepository;

  public List<Habit> getAllHabits() {
    return habitRepository.findAll();
  }

  public Optional<Habit> getHabitById(UUID id) {
    return habitRepository.findById(id);
  }

  public Habit createHabit(Habit habit) {
    return habitRepository.insert(habit);
  }

  public Optional<Habit> updateHabitName(UUID id, Habit newData) {
    return getHabitById(id)
        .map(
            existingHabit -> {
              existingHabit.setName(newData.getName());
              return habitRepository.save(existingHabit);
            });
  }

  public Boolean deleteHabitById(UUID id) {
    if (getHabitById(id).isPresent()) {
      habitRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
