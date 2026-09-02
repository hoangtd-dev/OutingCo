package com.outing.api.event.dto.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.outing.api.event.enums.BookingStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookingRequest(

		@NotNull int eventSessionId,

		@NotNull int clientId,

		Integer servicePriceId,

		BigDecimal price,

		BookingStatus status,

		Integer waitlistPosition,

		@Size(max = 500) String note,

		LocalDateTime checkedInAt,

		LocalDateTime checkedOutAt,

		@Size(max = 100) String absenceReason,

		Integer cancelledById,

		@Size(max = 255) String cancellationReason,

		LocalDateTime cancelledAt,

		Boolean isShortNoticeCancellation) {
}
