package com.outing.api.event.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EventRequest(

		@NotBlank @Size(max = 100) String name) {
}
