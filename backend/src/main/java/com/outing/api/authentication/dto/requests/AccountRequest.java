package com.outing.api.authentication.dto.requests;

import com.outing.api.authentication.enums.LoginMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountRequest(

		@NotNull int userId,

		@NotBlank @Size(max = 254) String email,

		@Size(max = 255) String password,

		@NotNull LoginMethod loginMethod,

		@Size(max = 255) String externalAuthenticationId) {
}
