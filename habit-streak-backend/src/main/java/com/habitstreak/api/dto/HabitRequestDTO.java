package com.habitstreak.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HabitRequestDTO {
  private String name;
  private Integer targetPerWeek;
}
