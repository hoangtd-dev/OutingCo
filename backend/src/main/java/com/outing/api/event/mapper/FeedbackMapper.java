package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.event.dto.requests.FeedbackRequest;
import com.outing.api.event.dto.responses.FeedbackResponse;
import com.outing.api.event.entities.Booking;
import com.outing.api.event.entities.Feedback;

@Component
public class FeedbackMapper {

	public FeedbackResponse toResponse(Feedback feedback) {
		return new FeedbackResponse(
				feedback.getId(),
				feedback.getBooking().getId(),
				feedback.getEngagementLevel(),
				feedback.getObservationNote());
	}

	public Feedback toEntity(FeedbackRequest request) {
		Feedback feedback = new Feedback();
		Booking booking = new Booking();
		booking.setId(request.bookingId());
		feedback.setBooking(booking);
		feedback.setEngagementLevel(request.engagementLevel());
		feedback.setObservationNote(request.observationNote());
		return feedback;
	}
}
