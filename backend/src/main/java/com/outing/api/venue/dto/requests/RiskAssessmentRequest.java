package com.outing.api.venue.dto.requests;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record RiskAssessmentRequest(

		@NotNull Integer venueId,

		@NotNull Integer templateId,

		Integer outingcoRefId,

		@NotNull BigDecimal riskScore,

		String expertAnalysis,

		LocalDateTime publishedAt,

		LocalDate validUntil,

		@NotNull Boolean isActive) {
}
