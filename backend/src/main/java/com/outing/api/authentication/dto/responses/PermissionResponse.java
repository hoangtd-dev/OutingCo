package com.outing.api.authentication.dto.responses;

public record PermissionResponse(
		int id,
		String resource,
		String feature,
		String action,
		String description) {
}
