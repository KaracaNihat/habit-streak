package com.habitstreak.api.service;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.repository.HabitRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitService {
  @Autowired private HabitRepository habitRepository;

  public List<Habit> getAllHabits() {
    return habitRepository.findAll();
  }

  public Optional<Habit> getHabitById(String id) {
    return habitRepository.findById(id);
  }
}
