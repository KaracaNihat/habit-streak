package com.habitstreak.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// ToDO xyz83

@Component
public class JwtUtil {

  private final Key signingKey;
  private final long jwtExpirationMs;

  public JwtUtil(
      @Value("${jwt.secret}") String jwtSecret,
      @Value("${jwt.expiration-ms}") long jwtExpirationMs) {

    if (jwtSecret.length() < 32) {
      throw new IllegalStateException("JWT secret must be at least 32 characters");
    }

    this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    this.jwtExpirationMs = jwtExpirationMs;
  }

  public String generateToken(String email) {
    return Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
        .signWith(signingKey)
        .compact();
  }

  public String extractEmail(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(signingKey)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}
