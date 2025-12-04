package com.habitstreak.api.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitResponseDTO {
  private UUID id;
  private String name;
  private Integer targetPerWeek;
  private List<LocalDate> completedDays;
  private LocalDate createdAt;
  private Integer streak;
}
