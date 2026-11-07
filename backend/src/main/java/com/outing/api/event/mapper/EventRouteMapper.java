package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.entities.User;
import com.outing.api.event.dto.requests.EventRouteRequest;
import com.outing.api.event.dto.responses.EventRouteResponse;
import com.outing.api.event.entities.EventRoute;
import com.outing.api.event.entities.EventSession;

@Component
public class EventRouteMapper {

	public EventRouteResponse toResponse(EventRoute eventRoute) {
		return new EventRouteResponse(
				eventRoute.getId(),
				eventRoute.getEventSession().getId(),
				eventRoute.getDriver().getId(),
				eventRoute.getStatus(),
				eventRoute.getTotalDistance(),
				eventRoute.getEstimatedDuration(),
				eventRoute.getDepartureAddress(),
				eventRoute.getDepartureTime(),
				eventRoute.getMapUrl());
	}

	public EventRoute toEntity(EventRouteRequest request) {
		EventRoute eventRoute = new EventRoute();
		EventSession eventSession = new EventSession();
		eventSession.setId(request.eventSessionId());
		eventRoute.setEventSession(eventSession);
		User driver = new User();
		driver.setId(request.driverId());
		eventRoute.setDriver(driver);
		if (request.status() != null) {
			eventRoute.setStatus(request.status());
		}
		eventRoute.setTotalDistance(request.totalDistance());
		eventRoute.setEstimatedDuration(request.estimatedDuration());
		eventRoute.setDepartureAddress(request.departureAddress());
		eventRoute.setDepartureTime(request.departureTime());
		eventRoute.setMapUrl(request.mapUrl());
		return eventRoute;
	}
}
