package com.habitstreak.api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.repository.HabitRepository;
import com.habitstreak.api.service.HabitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HabitServiceIntegrationTest {
  @Autowired private HabitService habitService;
  @Autowired private HabitRepository habitRepository;

  @BeforeEach
  void clean() {
    habitRepository.deleteAll();
  }

  @Test
  void testCreateAndGetHabit() {
    Habit habit = new Habit();
    habit.setName("Meditation");
    habit.setTargetPerWeek(5);

    Habit saved = habitService.createHabit(habit);
    Habit fetched = habitService.getHabitById(saved.getId());

    assertEquals(saved.getName(), fetched.getName());
    assertEquals(saved.getTargetPerWeek(), fetched.getTargetPerWeek());
  }
}
