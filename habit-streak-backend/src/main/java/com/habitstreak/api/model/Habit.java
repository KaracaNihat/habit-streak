package com.habitstreak.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Habit {
  @Id private UUID id = UUID.randomUUID();
  private String name;
  private Integer targetPerWeek;
  private List<LocalDate> completedDays = new ArrayList<>();
  private LocalDate createdAt = LocalDate.now();
  private Integer streak = 0;
}
