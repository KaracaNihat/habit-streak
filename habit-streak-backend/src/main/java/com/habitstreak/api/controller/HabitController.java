package com.habitstreak.api.controller;

import com.habitstreak.api.dto.HabitRequestDTO;
import com.habitstreak.api.dto.HabitResponseDTO;
import com.habitstreak.api.mapper.HabitMapper;
import com.habitstreak.api.model.Habit;
import com.habitstreak.api.service.HabitService;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/habits")
public class HabitController {
  private final HabitService habitService;
  private final HabitMapper habitMapper;

  @GetMapping
  public ResponseEntity<List<HabitResponseDTO>> getAllHabits() {
    List<Habit> habits = habitService.getAllHabits();
    List<HabitResponseDTO> habitResponseDTOS =
        habits.stream().map(habitMapper::toResponse).toList();
    return ResponseEntity.ok(habitResponseDTOS);
  }

  @GetMapping("/{id}")
  public ResponseEntity<HabitResponseDTO> getHabitById(@PathVariable String id) {
    Optional<HabitResponseDTO> dto = habitService.getHabitById(id).map(habitMapper::toResponse);
    return ResponseEntity.of(dto);
  }

  @PostMapping
  public ResponseEntity<HabitResponseDTO> createHabit(
      @Valid @RequestBody HabitRequestDTO habitRequestDTO) {
    Habit habit = habitService.createHabit(habitMapper.toModel(habitRequestDTO));
    HabitResponseDTO responseDTO = habitMapper.toResponse(habit);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<HabitResponseDTO> updateHabit(
      @PathVariable String id, @Valid @RequestBody HabitRequestDTO habitRequestDTO) {
    Optional<Habit> updatedHabit =
        habitService.updateHabitName(id, habitMapper.toModel(habitRequestDTO));
    return updatedHabit
        .map(habit -> ResponseEntity.accepted().body(habitMapper.toResponse(habit)))
        .orElseGet(() -> ResponseEntity.notFound().build());
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
