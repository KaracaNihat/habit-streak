package com.habitstreak.api.controller;

import com.habitstreak.api.dto.habit.HabitRequestDTO;
import com.habitstreak.api.dto.habit.HabitResponseDTO;
import com.habitstreak.api.mapper.HabitMapper;
import com.habitstreak.api.model.Habit;
import com.habitstreak.api.model.User;
import com.habitstreak.api.service.HabitService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/habits")
public class HabitController {
  private final HabitService habitService;
  private final HabitMapper habitMapper;

  @GetMapping
  public ResponseEntity<List<HabitResponseDTO>> getAllHabitsByUserId() {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Habit> habits = habitService.getAllHabits(currentUser.getId());
    List<HabitResponseDTO> habitResponseDTOS =
        habits.stream().map(habitMapper::toResponse).toList();
    return ResponseEntity.ok(habitResponseDTOS);
  }

  @GetMapping("/{id}")
  public ResponseEntity<HabitResponseDTO> getHabitById(@PathVariable UUID id) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    HabitResponseDTO dto =
        habitMapper.toResponse(habitService.getHabitById(id, currentUser.getId()));
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<HabitResponseDTO> createHabit(
      @Valid @RequestBody HabitRequestDTO habitRequestDTO) {
    // ToDo try extracting currentUser in root level for all method reduce duplication also in tests
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Habit habit =
        habitService.createHabit(habitMapper.toModel(habitRequestDTO), currentUser.getId());
    HabitResponseDTO responseDTO = habitMapper.toResponse(habit);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<HabitResponseDTO> updateHabit(
      @PathVariable UUID id, @Valid @RequestBody HabitRequestDTO habitRequestDTO) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Habit habit =
        habitService.updateHabit(id, habitMapper.toModel(habitRequestDTO), currentUser.getId());
    HabitResponseDTO updatedHabit = habitMapper.toResponse(habit);
    return ResponseEntity.ok(updatedHabit);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHabit(@PathVariable UUID id) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    habitService.deleteHabitById(id, currentUser.getId());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
