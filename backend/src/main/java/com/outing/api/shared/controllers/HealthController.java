package com.outing.api.shared.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

	private final String serverName;

	public HealthController(@Value("${app.server-name}") String serverName) {
		this.serverName = serverName;
	}

	@GetMapping
	public ResponseEntity<String> health() {
		return ResponseEntity.ok("server:" + serverName);
	}
}
