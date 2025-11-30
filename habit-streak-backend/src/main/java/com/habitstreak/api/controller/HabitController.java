package com.habitstreak.api.controller;

import com.habitstreak.api.model.Habit;
import com.habitstreak.api.service.HabitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HabitController {
  @Autowired private HabitService habitService;

  @GetMapping
  public ResponseEntity<List<Habit>> getAllHabits() {
    return new ResponseEntity<>(habitService.getAllHabits(), HttpStatus.OK);
  }
}
