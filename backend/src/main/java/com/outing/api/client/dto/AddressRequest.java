package com.outing.api.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequest(

		@NotBlank @Size(max = 255) String street,

		@NotBlank @Size(max = 100) String suburb,

		@NotBlank @Pattern(regexp = "[A-Z]{2,3}", message = "must be a state code such as NSW") String state,

		@NotBlank @Pattern(regexp = "[0-9]{4}", message = "must be 4 digits") String postcode) {
}
