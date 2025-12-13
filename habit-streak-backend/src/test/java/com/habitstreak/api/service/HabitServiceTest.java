package com.habitstreak.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.habitstreak.api.exception.AlreadyExistsException;
import com.habitstreak.api.exception.NotFoundException;
import com.habitstreak.api.model.Habit;
import com.habitstreak.api.repository.HabitRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {
  @Mock HabitRepository habitRepository;
  @InjectMocks HabitService habitService;
  private Habit habit;

  @BeforeEach
  void setUp() {
    habit = new Habit();
    habit.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    habit.setName("Workout");
    habit.setTargetPerWeek(3);
    habit.setCompletedDays(new ArrayList<>());
    habit.setStreak(0);
  }

  @Test
  void getAllHabitsShouldReturnListOfHabits() {
    List<Habit> allHabits = List.of(habit);
    when(habitRepository.findAll()).thenReturn(allHabits);

    List<Habit> result = habitService.getAllHabits();

    assertEquals(1, result.size());
    assertEquals(habit, result.get(0));
  }

  @Test
  void getHabitByIdShouldReturnHabit() {
    when(habitRepository.findById(habit.getId())).thenReturn(Optional.of(habit));

    Habit result = habitService.getHabitById(habit.getId());

    assertEquals(habit, result);
  }

  @Test
  void getHabitByIdWhenNotFoundShouldThrowNotFoundException() {
    UUID id = UUID.randomUUID();
    when(habitRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> habitService.getHabitById(id));
  }

  @Test
  void createHabitSuccess() {
    UUID userId = UUID.randomUUID();
    when(habitRepository.findByName(habit.getName())).thenReturn(Optional.empty());
    when(habitRepository.insert(habit)).thenReturn(habit);

    Habit result = habitService.createHabit(habit, userId);

    assertEquals(habit, result);
    verify(habitRepository).insert(habit);
  }

  @Test
  void attemptToCreateHabitWithExistingNameThrowsAlreadyExistsException() {
    UUID userId = UUID.randomUUID();
    when(habitRepository.findByName(habit.getName())).thenReturn(Optional.of(habit));

    assertThrows(AlreadyExistsException.class, () -> habitService.createHabit(habit, userId));
  }

  @Test
  void updateHabitSuccess() {
    UUID id = habit.getId();
    Habit newData = new Habit();
    newData.setName("Meditation");
    newData.setTargetPerWeek(5);
    newData.setCompletedDays(List.of());
    newData.setStreak(2);

    when(habitRepository.findById(id)).thenReturn(Optional.of(habit));
    when(habitRepository.save(habit)).thenReturn(habit);

    Habit result = habitService.updateHabit(id, newData);

    assertEquals("Meditation", result.getName());
    assertEquals(5, result.getTargetPerWeek());
    assertEquals(2, result.getStreak());
    verify(habitRepository).save(habit);
  }

  @Test
  void deleteHabitByIdSuccess() {
    UUID id = habit.getId();
    when(habitRepository.findById(id)).thenReturn(Optional.of(habit));
    doNothing().when(habitRepository).deleteById(id);

    assertDoesNotThrow(() -> habitService.deleteHabitById(id));
    verify(habitRepository).deleteById(id);
  }
}
