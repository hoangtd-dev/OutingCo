package com.outing.api.event.dto.responses;

public record FeedbackResponse(
		int id,
		int bookingId,
		Integer engagementLevel,
		String observationNote) {
}
