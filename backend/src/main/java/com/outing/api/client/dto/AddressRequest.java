package com.outing.api.client.dto;

import jakarta.validation.constraints.Size;

public record AddressRequest(

		@Size(max = 10) String addressNumber,

		@Size(max = 100) String addressLine,

		@Size(max = 85) String city,

		@Size(max = 100) String state,

		@Size(max = 20) String postcode,

		@Size(min = 2, max = 2) String country) {
}
