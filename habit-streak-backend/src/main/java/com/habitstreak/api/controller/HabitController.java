package com.habitstreak.api.controller;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.service.HabitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HabitController {
  @Autowired private HabitService habitService;

  @GetMapping
  public ResponseEntity<List<Habit>> getAllHabits() {
    return new ResponseEntity<>(habitService.getAllHabits(), HttpStatus.OK);
  }

  @GetMapping("/habit/{id}")
  public ResponseEntity<Habit> getHabitById(@PathVariable String id) {
    return ResponseEntity.of(habitService.getHabitById(id));
  }

  @PostMapping
  public ResponseEntity<Habit> createHabit(@RequestBody Habit habit) {
    Habit createdHabit = habitService.createHabit(habit);
    return new ResponseEntity<>(createdHabit, HttpStatus.CREATED);
  }

  @PutMapping("/habit/{id}")
  public ResponseEntity<Habit> updateHabit(@PathVariable String id, @RequestBody Habit habit) {
    return ResponseEntity.of(habitService.updateHabitName(id, habit));
  }
}
