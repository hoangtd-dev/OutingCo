package com.outing.api.authentication.dto.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record UserRequest(

		@NotBlank @Size(max = 50) String firstName,

		@NotBlank @Size(max = 100) String lastName,

		@Past LocalDate dateOfBirth,

		@NotBlank @Size(max = 254) String email,

		@Size(max = 15) String phone,

		Boolean isActive) {
}
