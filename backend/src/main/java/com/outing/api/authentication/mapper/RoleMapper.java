package com.outing.api.authentication.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.dto.requests.RoleRequest;
import com.outing.api.authentication.dto.responses.RoleResponse;
import com.outing.api.authentication.entities.Role;

@Component
public class RoleMapper {

	public RoleResponse toResponse(Role role) {
		return new RoleResponse(role.getId(), role.getRoleName(), role.getDescription());
	}

	public Role toEntity(RoleRequest request) {
		Role role = new Role();
		role.setRoleName(request.roleName());
		role.setDescription(request.description());
		return role;
	}
}
