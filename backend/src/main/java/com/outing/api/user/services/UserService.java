package com.outing.api.user.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.user.dto.Requests.UserRequest;
import com.outing.api.user.dto.Requests.UserUpdatedRequest;
import com.outing.api.user.dto.Responses.UserResponse;
import com.outing.api.user.mapper.UserMapper;
import com.outing.api.user.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public List<UserResponse> getUsers() {
		var users = userRepository.findAll();

		return userMapper.toResponse(users);
	}

	public void createUser(UserRequest request) {
		var userEntity = userMapper.toEntity(request);

		userRepository.save(userEntity);
	}

	@Transactional
	public void updateUser(int id, UserUpdatedRequest request) {
		var userEntity = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found " + id));

		var updatedEntity = userMapper.toEntityUpdated(request, userEntity);
		userRepository.save(updatedEntity);
	}

	@Transactional
	public void deleteUser(int id) {
		var userEntity = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found " + id));

		userEntity.setDeleted(true);
		userRepository.save(userEntity);
	}
}