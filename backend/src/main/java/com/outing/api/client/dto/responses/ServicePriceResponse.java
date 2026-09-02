package com.outing.api.client.dto.responses;

import java.math.BigDecimal;

public record ServicePriceResponse(
		int id,
		String name,
		BigDecimal price,
		boolean isActive) {
}
