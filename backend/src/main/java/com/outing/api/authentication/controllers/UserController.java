package com.outing.api.authentication.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.authentication.dto.requests.UserRequest;
import com.outing.api.authentication.dto.responses.UserResponse;
import com.outing.api.authentication.entities.User;
import com.outing.api.authentication.mapper.UserMapper;
import com.outing.api.authentication.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserRepository userRepository;
	private final UserMapper mapper;

	public UserController(UserRepository userRepository, UserMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<UserResponse>> getUsers(Pageable pageable) {
		return ResponseEntity.ok(userRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(user));
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody UserRequest request) {
		userRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
		user.setIsDeleted(true);
		userRepository.save(user);
		return ResponseEntity.noContent().build();
	}
}
