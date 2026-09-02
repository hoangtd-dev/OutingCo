package com.outing.api.authentication.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.dto.requests.PermissionRequest;
import com.outing.api.authentication.dto.responses.PermissionResponse;
import com.outing.api.authentication.entities.Permission;

@Component
public class PermissionMapper {

	public PermissionResponse toResponse(Permission permission) {
		return new PermissionResponse(
				permission.getId(),
				permission.getResource(),
				permission.getFeature(),
				permission.getAction(),
				permission.getDescription());
	}

	public Permission toEntity(PermissionRequest request) {
		Permission permission = new Permission();
		permission.setResource(request.resource());
		permission.setFeature(request.feature());
		permission.setAction(request.action());
		permission.setDescription(request.description());
		return permission;
	}
}
