package com.outing.api.event.dto.requests;

import java.time.LocalDateTime;

import com.outing.api.notification.enums.EmailStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventEmailRequest(

		@NotNull int eventSessionId,

		Integer emailTemplateId,

		@Size(max = 100) String subject,

		String body,

		EmailStatus status,

		Integer approvedById,

		LocalDateTime approvedAt,

		LocalDateTime sentAt) {
}
