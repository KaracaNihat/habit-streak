package com.habitstreak.api.model;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

  @Id private UUID id = UUID.randomUUID();

  @Indexed(unique = true)
  private String email;

  private String password;
}
