package com.habitstreak.api.repository;

import com.habitstreak.api.model.Habit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HabitRepository extends MongoRepository<Habit, String> {}
