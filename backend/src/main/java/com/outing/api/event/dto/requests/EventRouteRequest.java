package com.outing.api.event.dto.requests;

import java.time.LocalDateTime;

import com.outing.api.event.enums.RouteStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventRouteRequest(

		@NotNull int eventSessionId,

		@NotNull int driverId,

		RouteStatus status,

		Float totalDistance,

		Integer estimatedDuration,

		@Size(max = 255) String departureAddress,

		LocalDateTime departureTime,

		String mapUrl) {
}
