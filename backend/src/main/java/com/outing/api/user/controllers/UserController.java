package com.outing.api.user.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.user.dto.Requests.UserRequest;
import com.outing.api.user.dto.Requests.UserUpdatedRequest;
import com.outing.api.user.dto.Responses.UserResponse;
import com.outing.api.user.entities.User;
import com.outing.api.user.mapper.UserMapper;
import com.outing.api.user.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	public UserController(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(userMapper.toResponse(users));
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody UserRequest request) {
		User userEntity = userMapper.toEntity(request);
		userRepository.save(userEntity);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserUpdatedRequest updatedRequest) {
		User userEntity = requireUser(id);
		User updatedEntity = userMapper.toEntityUpdated(updatedRequest, userEntity);
		userRepository.save(updatedEntity);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		User userEntity = requireUser(id);
		userEntity.setDeleted(true);
		userRepository.save(userEntity);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	private User requireUser(int id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found " + id));
	}
}
