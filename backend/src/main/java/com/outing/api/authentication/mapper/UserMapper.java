package com.outing.api.authentication.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.dto.requests.UserRequest;
import com.outing.api.authentication.dto.responses.UserResponse;
import com.outing.api.authentication.entities.User;

@Component
public class UserMapper {

	public UserResponse toResponse(User user) {
		return new UserResponse(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getDateOfBirth(),
				user.getEmail(),
				user.getPhone(),
				user.getIsActive());
	}

	public User toEntity(UserRequest request) {
		User user = new User();
		user.setFirstName(request.firstName());
		user.setLastName(request.lastName());
		user.setDateOfBirth(request.dateOfBirth());
		user.setEmail(request.email());
		user.setPhone(request.phone());
		if (request.isActive() != null) {
			user.setIsActive(request.isActive());
		}
		return user;
	}
}
