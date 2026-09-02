package com.outing.api.notification.dto.responses;

public record EmailTemplateResponse(
		int id,
		String name,
		String layout) {
}
