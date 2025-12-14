package com.habitstreak.api.controller;

import com.habitstreak.api.dto.auth.AuthResponse;
import com.habitstreak.api.dto.auth.LoginRequest;
import com.habitstreak.api.dto.auth.RegisterRequest;
import com.habitstreak.api.model.User;
import com.habitstreak.api.repository.UserRepository;
import com.habitstreak.api.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  @PostMapping("/register")
  public void register(@Valid @RequestBody RegisterRequest request) {
    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user);
  }

  @PostMapping("/login")
  public AuthResponse login(@Valid @RequestBody LoginRequest request) {
    User user =
        userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }
    return new AuthResponse(jwtUtil.generateToken(user.getEmail()));
  }
}
