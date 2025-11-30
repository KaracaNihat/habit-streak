package com.habitstreak.api.model;

import java.util.List;
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
  @Id private String id;
  private String name;
  private Integer targetPerWeek;
  private List<String> completedDays;
  private String createdAt;
  private Integer streak;
}
