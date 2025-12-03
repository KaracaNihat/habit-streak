package com.habitstreak.api.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitResponseDTO {
  private String name;
  private Integer targetPerWeek;
  private List<LocalDate> completedDays;
  private LocalDate createdAt;
  private Integer streak;
}
