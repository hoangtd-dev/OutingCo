package com.outing.api.client.dto.requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ServicePriceRequest(

		@NotBlank @Size(max = 100) String name,

		@NotNull BigDecimal price,

		Boolean isActive) {
}
