package com.outing.api.event.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.outing.api.event.enums.StopStatus;

public record EventRouteStopResponse(
		int id,
		int routeId,
		int clientId,
		BigDecimal latitude,
		BigDecimal longitude,
		Integer stopOrder,
		LocalDateTime estimatedPickupAt,
		LocalDateTime actualPickupAt,
		StopStatus status,
		String note) {
}
