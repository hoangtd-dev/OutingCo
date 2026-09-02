package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.event.dto.requests.EventSessionRequest;
import com.outing.api.event.dto.responses.EventSessionResponse;
import com.outing.api.event.entities.Event;
import com.outing.api.event.entities.EventSession;
import com.outing.api.venue.entities.Venue;

@Component
public class EventSessionMapper {

	public EventSessionResponse toResponse(EventSession eventSession) {
		return new EventSessionResponse(
				eventSession.getId(),
				eventSession.getEvent().getId(),
				eventSession.getVenue() != null ? eventSession.getVenue().getId() : null,
				eventSession.getDescription(),
				eventSession.getEventDate(),
				eventSession.getStartTime(),
				eventSession.getEndTime(),
				eventSession.getMaxParticipant(),
				eventSession.getRegistrationToken(),
				eventSession.getRegistrationClosesAt(),
				eventSession.getStatus(),
				eventSession.getActualStartAt(),
				eventSession.getActualEndAt());
	}

	public EventSession toEntity(EventSessionRequest request) {
		EventSession eventSession = new EventSession();
		Event event = new Event();
		event.setId(request.eventId());
		eventSession.setEvent(event);
		if (request.venueId() != null) {
			Venue venue = new Venue();
			venue.setId(request.venueId());
			eventSession.setVenue(venue);
		}
		eventSession.setDescription(request.description());
		eventSession.setEventDate(request.eventDate());
		eventSession.setStartTime(request.startTime());
		eventSession.setEndTime(request.endTime());
		eventSession.setMaxParticipant(request.maxParticipant());
		eventSession.setRegistrationToken(request.registrationToken());
		eventSession.setRegistrationClosesAt(request.registrationClosesAt());
		if (request.status() != null) {
			eventSession.setStatus(request.status());
		}
		eventSession.setActualStartAt(request.actualStartAt());
		eventSession.setActualEndAt(request.actualEndAt());
		return eventSession;
	}
}
