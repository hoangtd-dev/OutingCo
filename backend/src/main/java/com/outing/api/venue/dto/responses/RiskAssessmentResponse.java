package com.outing.api.venue.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RiskAssessmentResponse(
		int id,
		int venueId,
		int templateId,
		Integer outingcoRefId,
		BigDecimal riskScore,
		String expertAnalysis,
		LocalDateTime publishedAt,
		LocalDate validUntil,
		Boolean isActive) {
}
