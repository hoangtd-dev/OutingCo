package com.outing.api.authentication.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.dto.requests.UserRoleRequest;
import com.outing.api.authentication.dto.responses.UserRoleResponse;
import com.outing.api.authentication.entities.Role;
import com.outing.api.authentication.entities.User;
import com.outing.api.authentication.entities.UserRole;
import com.outing.api.authentication.entities.compositeKey.UserRoleId;

@Component
public class UserRoleMapper {

	public UserRoleResponse toResponse(UserRole userRole) {
		return new UserRoleResponse(
				userRole.getUser().getId(),
				userRole.getRole().getId());
	}

	public UserRole toEntity(UserRoleRequest request) {
		UserRole userRole = new UserRole();
		userRole.setId(new UserRoleId(request.userId(), request.roleId()));
		User user = new User();
		user.setId(request.userId());
		userRole.setUser(user);
		Role role = new Role();
		role.setId(request.roleId());
		userRole.setRole(role);
		return userRole;
	}
}
