package com.outing.api.authentication.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.dto.requests.RolePermissionRequest;
import com.outing.api.authentication.dto.responses.RolePermissionResponse;
import com.outing.api.authentication.entities.Permission;
import com.outing.api.authentication.entities.Role;
import com.outing.api.authentication.entities.RolePermission;
import com.outing.api.authentication.entities.compositeKey.RolePermissionId;

@Component
public class RolePermissionMapper {

	public RolePermissionResponse toResponse(RolePermission rolePermission) {
		return new RolePermissionResponse(
				rolePermission.getRole().getId(),
				rolePermission.getPermission().getId());
	}

	public RolePermission toEntity(RolePermissionRequest request) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setId(new RolePermissionId(request.roleId(), request.permissionId()));
		Role role = new Role();
		role.setId(request.roleId());
		rolePermission.setRole(role);
		Permission permission = new Permission();
		permission.setId(request.permissionId());
		rolePermission.setPermission(permission);
		return rolePermission;
	}
}
