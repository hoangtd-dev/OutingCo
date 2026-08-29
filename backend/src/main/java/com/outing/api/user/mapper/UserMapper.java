package com.outing.api.user.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.outing.api.user.dto.Requests.UserRequest;
import com.outing.api.user.dto.Requests.UserUpdatedRequest;
import com.outing.api.user.dto.Responses.UserResponse;
import com.outing.api.user.entities.User;

@Component
public class UserMapper {

	public UserResponse toResponse(User user) {
		return new UserResponse(user.getId(), user.getName());
	}

	public List<UserResponse> toResponse(List<User> users) {
		return users.stream().map(this::toResponse).toList();
	}

	public User toEntity(UserRequest request) {
		User user = new User();
		user.setName(request.getName());
		return user;
	}

	public User toEntityUpdated(UserUpdatedRequest request, User user) {
		user.setName(request.getName());
		return user;
	}
}
