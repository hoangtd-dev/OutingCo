package com.outing.api.authentication.dto.responses;

import java.time.LocalDate;

public record UserResponse(
		int id,
		String firstName,
		String lastName,
		LocalDate dateOfBirth,
		String email,
		String phone,
		boolean isActive) {
}
