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

import com.outing.api.authentication.dto.requests.RolePermissionRequest;
import com.outing.api.authentication.dto.responses.RolePermissionResponse;
import com.outing.api.authentication.entities.RolePermission;
import com.outing.api.authentication.entities.compositeKey.RolePermissionId;
import com.outing.api.authentication.mapper.RolePermissionMapper;
import com.outing.api.authentication.repositories.RolePermissionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/role-permissions")
public class RolePermissionController {

	private final RolePermissionRepository rolePermissionRepository;
	private final RolePermissionMapper mapper;

	public RolePermissionController(RolePermissionRepository rolePermissionRepository, RolePermissionMapper mapper) {
		this.rolePermissionRepository = rolePermissionRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<RolePermissionResponse>> getRolePermissions(Pageable pageable) {
		return ResponseEntity.ok(rolePermissionRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{roleId}/{permissionId}")
	public ResponseEntity<RolePermissionResponse> getRolePermission(@PathVariable int roleId,
			@PathVariable int permissionId) {
		RolePermission rolePermission = rolePermissionRepository.findById(new RolePermissionId(roleId, permissionId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"RolePermission not found: " + roleId + "/" + permissionId));
		return ResponseEntity.ok(mapper.toResponse(rolePermission));
	}

	@PostMapping
	public ResponseEntity<Void> createRolePermission(@Valid @RequestBody RolePermissionRequest request) {
		rolePermissionRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{roleId}/{permissionId}")
	public ResponseEntity<Void> deleteRolePermission(@PathVariable int roleId, @PathVariable int permissionId) {
		RolePermissionId id = new RolePermissionId(roleId, permissionId);
		if (!rolePermissionRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"RolePermission not found: " + roleId + "/" + permissionId);
		}
		rolePermissionRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
