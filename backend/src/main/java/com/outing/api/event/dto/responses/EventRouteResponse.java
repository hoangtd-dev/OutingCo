package com.outing.api.event.dto.responses;

import java.time.LocalDateTime;

import com.outing.api.event.enums.RouteStatus;

public record EventRouteResponse(
		int id,
		int eventSessionId,
		int driverId,
		RouteStatus status,
		Float totalDistance,
		Integer estimatedDuration,
		String departureAddress,
		LocalDateTime departureTime,
		String mapUrl) {
}
