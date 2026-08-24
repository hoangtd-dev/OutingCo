package com.outing.api.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.outing.api.user.dto.Requests.UserRequest;
import com.outing.api.user.dto.Requests.UserUpdatedRequest;
import com.outing.api.user.dto.Responses.UserResponse;
import com.outing.api.user.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	List<UserResponse> toResponse(List<User> user);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	User toEntity(UserRequest user);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	User toEntityUpdated(UserUpdatedRequest updatedUser, @MappingTarget User user);
}
