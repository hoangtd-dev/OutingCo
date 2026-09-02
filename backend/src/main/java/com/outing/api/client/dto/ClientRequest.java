package com.outing.api.client.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record ClientRequest(

		@NotNull int caseManagerId,

		@NotBlank @Size(max = 50) String firstName,

		@NotBlank @Size(max = 100) String lastName,

		@NotBlank @Size(max = 254) String email,

		@Valid AddressRequest address,

		@Past LocalDate dateOfBirth,

		@Size(max = 9) String clientNumber,

		@Size(max = 15) String phone,

		@Size(max = 150) String emergencyContactName,

		@Size(max = 50) String emergencyContactRelationship,

		@Size(max = 20) String emergencyContactPhonePrimary,

		Boolean isActive) {
}
