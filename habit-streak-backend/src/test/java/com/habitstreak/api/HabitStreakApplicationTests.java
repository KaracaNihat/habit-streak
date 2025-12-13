package com.habitstreak.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
    properties = {
      "jwt.secret=test-super-long-secret-key-at-least-32-bytes-123",
      "jwt.expiration-ms=86400000"
    })
class HabitStreakApplicationTests {

  @Test
  void contextLoads() {}
}
