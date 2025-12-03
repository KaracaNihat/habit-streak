package com.habitstreak.api.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HabitRequestDTO {
  private String name;
  private Integer targetPerWeek;
  private List<LocalDate> completedDays;
  private Integer streak;
}
