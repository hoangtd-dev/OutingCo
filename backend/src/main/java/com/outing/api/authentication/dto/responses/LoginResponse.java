package com.outing.api.authentication.dto.responses;

import java.time.Instant;
import java.util.List;

public record LoginResponse(
		String token,
		Instant expiresAt,
		String email,
		List<String> roles) {
}
