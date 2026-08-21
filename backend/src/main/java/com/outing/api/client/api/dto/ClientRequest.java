package com.outing.api.client.api.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record ClientRequest(

		@NotBlank @Size(max = 100) String firstName,

		@NotBlank @Size(max = 100) String lastName,

		@NotNull @Past LocalDate dateOfBirth,

		@Size(max = 20) String phoneNumber,

		@Valid AddressRequest address) {
}
