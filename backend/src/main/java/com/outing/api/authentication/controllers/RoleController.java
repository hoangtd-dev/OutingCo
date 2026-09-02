package com.outing.api.authentication.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.outing.api.authentication.dto.requests.RoleRequest;
import com.outing.api.authentication.dto.responses.RoleResponse;
import com.outing.api.authentication.entities.Role;
import com.outing.api.authentication.mapper.RoleMapper;
import com.outing.api.authentication.repositories.RoleRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

	private final RoleRepository roleRepository;
	private final RoleMapper mapper;

	public RoleController(RoleRepository roleRepository, RoleMapper mapper) {
		this.roleRepository = roleRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<RoleResponse>> getRoles(Pageable pageable) {
		return ResponseEntity.ok(roleRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleResponse> getRole(@PathVariable int id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(role));
	}

	@PostMapping
	public ResponseEntity<Void> createRole(@Valid @RequestBody RoleRequest request) {
		roleRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> patchRole(@PathVariable int id, @Valid @RequestBody RoleRequest request) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found: " + id));
		role.setRoleName(request.roleName());
		role.setDescription(request.description());
		roleRepository.save(role);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable int id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found: " + id));
		role.setIsDeleted(true);
		roleRepository.save(role);
		return ResponseEntity.noContent().build();
	}
}
