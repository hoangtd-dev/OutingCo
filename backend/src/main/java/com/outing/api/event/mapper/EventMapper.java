package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.event.dto.requests.EventRequest;
import com.outing.api.event.dto.responses.EventResponse;
import com.outing.api.event.entities.Event;

@Component
public class EventMapper {

	public EventResponse toResponse(Event event) {
		return new EventResponse(event.getId(), event.getName());
	}

	public Event toEntity(EventRequest request) {
		Event event = new Event();
		event.setName(request.name());
		return event;
	}
}
