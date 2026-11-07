package com.outing.api.event.dto.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.outing.api.event.enums.SessionStatus;

public record EventSessionResponse(
		int id,
		int eventId,
		Integer venueId,
		String description,
		LocalDate eventDate,
		LocalTime startTime,
		LocalTime endTime,
		Integer maxParticipant,
		String registrationToken,
		LocalDateTime registrationClosesAt,
		SessionStatus status,
		LocalDateTime actualStartAt,
		LocalDateTime actualEndAt) {
}
