package com.outing.api.authentication.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final SecretKey key;

	private final long expirationMinutes;

	public JwtService(@Value("${app.jwt.secret}") String secret,
			@Value("${app.jwt.expiration-minutes}") long expirationMinutes) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.expirationMinutes = expirationMinutes;
	}

	public String generateToken(String email, List<String> roles) {
		Instant now = Instant.now();
		return Jwts.builder()
				.subject(email)
				.claim("roles", roles)
				.issuedAt(Date.from(now))
				.expiration(Date.from(expiresAt(now)))
				.signWith(key)
				.compact();
	}

	public Instant expiresAt(Instant from) {
		return from.plus(expirationMinutes, ChronoUnit.MINUTES);
	}

	public long getExpirationMinutes() {
		return expirationMinutes;
	}

	public Claims parse(String token) throws JwtException {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	@SuppressWarnings("unchecked")
	public List<String> extractRoles(Claims claims) {
		Object roles = claims.get("roles");
		return roles instanceof List ? (List<String>) roles : List.of();
	}
}
