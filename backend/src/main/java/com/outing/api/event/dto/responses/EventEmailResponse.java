package com.outing.api.event.dto.responses;

import java.time.LocalDateTime;

import com.outing.api.notification.enums.EmailStatus;

public record EventEmailResponse(
		int id,
		int eventSessionId,
		Integer emailTemplateId,
		String subject,
		String body,
		EmailStatus status,
		Integer approvedById,
		LocalDateTime approvedAt,
		LocalDateTime sentAt) {
}
