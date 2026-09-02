package com.outing.api.authentication.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.outing.api.authentication.dto.responses.PermissionResponse;
import com.outing.api.authentication.mapper.PermissionMapper;
import com.outing.api.authentication.repositories.PermissionRepository;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

	private final PermissionRepository permissionRepository;
	private final PermissionMapper mapper;

	public PermissionController(PermissionRepository permissionRepository, PermissionMapper mapper) {
		this.permissionRepository = permissionRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<PermissionResponse>> getPermissions(Pageable pageable) {
		return ResponseEntity.ok(permissionRepository.findAll(pageable).map(mapper::toResponse));
	}
}
