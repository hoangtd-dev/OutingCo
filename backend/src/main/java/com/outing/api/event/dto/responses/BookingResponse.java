package com.outing.api.event.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.outing.api.event.enums.BookingStatus;

public record BookingResponse(
		int id,
		int eventSessionId,
		int clientId,
		Integer servicePriceId,
		BigDecimal price,
		BookingStatus status,
		Integer waitlistPosition,
		String note,
		LocalDateTime checkedInAt,
		LocalDateTime checkedOutAt,
		String absenceReason,
		Integer cancelledById,
		String cancellationReason,
		LocalDateTime cancelledAt,
		boolean isShortNoticeCancellation) {
}
