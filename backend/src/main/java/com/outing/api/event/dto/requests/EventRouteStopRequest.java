package com.outing.api.event.dto.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.outing.api.event.enums.StopStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventRouteStopRequest(

		@NotNull int routeId,

		@NotNull int clientId,

		BigDecimal latitude,

		BigDecimal longitude,

		Integer stopOrder,

		LocalDateTime estimatedPickupAt,

		LocalDateTime actualPickupAt,

		StopStatus status,

		@Size(max = 255) String note) {
}
