package com.habitstreak.api.dto.habit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HabitRequestDTO {
  @NotBlank private String name;
  @Positive private Integer targetPerWeek;
  private List<LocalDate> completedDays;
  private Integer streak;
}
