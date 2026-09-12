package com.outing.api.authentication.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

		@NotBlank @Size(max = 50) String firstName,

		@NotBlank @Size(max = 100) String lastName,

		@NotBlank @Email @Size(max = 254) String email,

		@NotBlank @Size(min = 8, max = 255) String password) {
}
