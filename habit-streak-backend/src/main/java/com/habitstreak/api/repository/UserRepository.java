package com.habitstreak.api.repository;

import com.habitstreak.api.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}
