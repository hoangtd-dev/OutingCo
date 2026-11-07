package com.outing.api.event.dto.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.outing.api.event.enums.SessionStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventSessionRequest(

		@NotNull int eventId,

		Integer venueId,

		String description,

		@NotNull LocalDate eventDate,

		LocalTime startTime,

		LocalTime endTime,

		@NotNull Integer maxParticipant,

		@NotBlank @Size(max = 64) String registrationToken,

		LocalDateTime registrationClosesAt,

		SessionStatus status,

		LocalDateTime actualStartAt,

		LocalDateTime actualEndAt) {
}
