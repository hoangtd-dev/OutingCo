package com.outing.api.authentication.dto.responses;

import com.outing.api.authentication.enums.LoginMethod;

public record AccountResponse(
		int id,
		int userId,
		String email,
		LoginMethod loginMethod,
		String externalAuthenticationId) {
}
