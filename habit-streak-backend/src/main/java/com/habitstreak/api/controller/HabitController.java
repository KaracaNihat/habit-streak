package com.habitstreak.api.controller;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.service.HabitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
  private final HabitService habitService;

  public HabitController(HabitService habitService) {
    this.habitService = habitService;
  }

  @GetMapping
  public ResponseEntity<List<Habit>> getAllHabits() {
    return new ResponseEntity<>(habitService.getAllHabits(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Habit> getHabitById(@PathVariable String id) {
    return ResponseEntity.of(habitService.getHabitById(id));
  }

  @PostMapping
  public ResponseEntity<Habit> createHabit(@RequestBody Habit habit) {
    Habit createdHabit = habitService.createHabit(habit);
    return new ResponseEntity<>(createdHabit, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Habit> updateHabit(@PathVariable String id, @RequestBody Habit habit) {
    return ResponseEntity.of(habitService.updateHabitName(id, habit));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHabit(@PathVariable String id) {
    if (habitService.deleteHabitById(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
