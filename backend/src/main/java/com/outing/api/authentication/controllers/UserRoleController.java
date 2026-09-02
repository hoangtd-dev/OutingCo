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

import com.outing.api.authentication.dto.requests.UserRoleRequest;
import com.outing.api.authentication.dto.responses.UserRoleResponse;
import com.outing.api.authentication.entities.UserRole;
import com.outing.api.authentication.entities.compositeKey.UserRoleId;
import com.outing.api.authentication.mapper.UserRoleMapper;
import com.outing.api.authentication.repositories.UserRoleRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user-roles")
public class UserRoleController {

	private final UserRoleRepository userRoleRepository;
	private final UserRoleMapper mapper;

	public UserRoleController(UserRoleRepository userRoleRepository, UserRoleMapper mapper) {
		this.userRoleRepository = userRoleRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<UserRoleResponse>> getUserRoles(Pageable pageable) {
		return ResponseEntity.ok(userRoleRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{userId}/{roleId}")
	public ResponseEntity<UserRoleResponse> getUserRole(@PathVariable int userId, @PathVariable int roleId) {
		UserRole userRole = userRoleRepository.findById(new UserRoleId(userId, roleId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"UserRole not found: " + userId + "/" + roleId));
		return ResponseEntity.ok(mapper.toResponse(userRole));
	}

	@PostMapping
	public ResponseEntity<Void> createUserRole(@Valid @RequestBody UserRoleRequest request) {
		userRoleRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{userId}/{roleId}")
	public ResponseEntity<Void> deleteUserRole(@PathVariable int userId, @PathVariable int roleId) {
		UserRoleId id = new UserRoleId(userId, roleId);
		if (!userRoleRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserRole not found: " + userId + "/" + roleId);
		}
		userRoleRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
