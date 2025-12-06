package com.habitstreak.api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.repository.HabitRepository;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

@DataMongoTest
@ActiveProfiles("test")
public class HabitRepositoryIntegrationTest {
  @Autowired HabitRepository habitRepository;
  private Habit habit;

  @BeforeEach
  void setUp() {
    habitRepository.deleteAll();
    habit = new Habit();
    habit.setId(UUID.randomUUID());
    habit.setName("Workout");
    habit.setTargetPerWeek(3);
    habit.setCompletedDays(new ArrayList<>());
    habit.setStreak(0);
    habitRepository.save(habit);
  }

  @Test
  void testFindById() {
    Optional<Habit> found = habitRepository.findById(habit.getId());
    assertTrue(found.isPresent());
    assertEquals("Workout", found.get().getName());
  }

  @Test
  void testFindByName() {
    Optional<Habit> found = habitRepository.findByName("Workout");
    assertTrue(found.isPresent());
  }
}
