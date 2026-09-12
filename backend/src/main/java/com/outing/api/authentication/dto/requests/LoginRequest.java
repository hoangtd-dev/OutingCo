package com.outing.api.authentication.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

		@NotBlank @Size(max = 254) String email,

		@NotBlank @Size(max = 255) String password) {
}
