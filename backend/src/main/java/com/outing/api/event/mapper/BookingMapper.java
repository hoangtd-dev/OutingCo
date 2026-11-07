package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.entities.User;
import com.outing.api.client.entities.Client;
import com.outing.api.client.entities.ServicePrice;
import com.outing.api.event.dto.requests.BookingRequest;
import com.outing.api.event.dto.responses.BookingResponse;
import com.outing.api.event.entities.Booking;
import com.outing.api.event.entities.EventSession;

@Component
public class BookingMapper {

	public BookingResponse toResponse(Booking booking) {
		return new BookingResponse(
				booking.getId(),
				booking.getEventSession().getId(),
				booking.getClient().getId(),
				booking.getServicePrice() != null ? booking.getServicePrice().getId() : null,
				booking.getPrice(),
				booking.getStatus(),
				booking.getWaitlistPosition(),
				booking.getNote(),
				booking.getCheckedInAt(),
				booking.getCheckedOutAt(),
				booking.getAbsenceReason(),
				booking.getCancelledBy() != null ? booking.getCancelledBy().getId() : null,
				booking.getCancellationReason(),
				booking.getCancelledAt(),
				booking.getIsShortNoticeCancellation());
	}

	public Booking toEntity(BookingRequest request) {
		Booking booking = new Booking();
		EventSession eventSession = new EventSession();
		eventSession.setId(request.eventSessionId());
		booking.setEventSession(eventSession);
		Client client = new Client();
		client.setId(request.clientId());
		booking.setClient(client);
		if (request.servicePriceId() != null) {
			ServicePrice servicePrice = new ServicePrice();
			servicePrice.setId(request.servicePriceId());
			booking.setServicePrice(servicePrice);
		}
		booking.setPrice(request.price());
		if (request.status() != null) {
			booking.setStatus(request.status());
		}
		booking.setWaitlistPosition(request.waitlistPosition());
		booking.setNote(request.note());
		booking.setCheckedInAt(request.checkedInAt());
		booking.setCheckedOutAt(request.checkedOutAt());
		booking.setAbsenceReason(request.absenceReason());
		if (request.cancelledById() != null) {
			User cancelledBy = new User();
			cancelledBy.setId(request.cancelledById());
			booking.setCancelledBy(cancelledBy);
		}
		booking.setCancellationReason(request.cancellationReason());
		booking.setCancelledAt(request.cancelledAt());
		if (request.isShortNoticeCancellation() != null) {
			booking.setIsShortNoticeCancellation(request.isShortNoticeCancellation());
		}
		return booking;
	}
}
