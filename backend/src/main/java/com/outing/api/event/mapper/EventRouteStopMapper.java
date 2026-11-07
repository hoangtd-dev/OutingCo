package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.client.entities.Client;
import com.outing.api.event.dto.requests.EventRouteStopRequest;
import com.outing.api.event.dto.responses.EventRouteStopResponse;
import com.outing.api.event.entities.EventRoute;
import com.outing.api.event.entities.EventRouteStop;

@Component
public class EventRouteStopMapper {

	public EventRouteStopResponse toResponse(EventRouteStop eventRouteStop) {
		return new EventRouteStopResponse(
				eventRouteStop.getId(),
				eventRouteStop.getRoute().getId(),
				eventRouteStop.getClient().getId(),
				eventRouteStop.getLatitude(),
				eventRouteStop.getLongitude(),
				eventRouteStop.getStopOrder(),
				eventRouteStop.getEstimatedPickupAt(),
				eventRouteStop.getActualPickupAt(),
				eventRouteStop.getStatus(),
				eventRouteStop.getNote());
	}

	public EventRouteStop toEntity(EventRouteStopRequest request) {
		EventRouteStop eventRouteStop = new EventRouteStop();
		EventRoute route = new EventRoute();
		route.setId(request.routeId());
		eventRouteStop.setRoute(route);
		Client client = new Client();
		client.setId(request.clientId());
		eventRouteStop.setClient(client);
		eventRouteStop.setLatitude(request.latitude());
		eventRouteStop.setLongitude(request.longitude());
		if (request.stopOrder() != null) {
			eventRouteStop.setStopOrder(request.stopOrder());
		}
		eventRouteStop.setEstimatedPickupAt(request.estimatedPickupAt());
		eventRouteStop.setActualPickupAt(request.actualPickupAt());
		if (request.status() != null) {
			eventRouteStop.setStatus(request.status());
		}
		eventRouteStop.setNote(request.note());
		return eventRouteStop;
	}
}
