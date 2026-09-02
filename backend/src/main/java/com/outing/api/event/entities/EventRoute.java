package com.outing.api.event.entities;

import java.time.LocalDateTime;

import com.outing.api.authentication.entities.User;
import com.outing.api.event.enums.RouteStatus;
import com.outing.api.shared.entities.SoftDeleteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_routes")
@Getter
@Setter
@NoArgsConstructor
public class EventRoute extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_session_id", nullable = false)
	private EventSession eventSession;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driver_id", nullable = false)
	private User driver;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private RouteStatus status = RouteStatus.DRAFT;

	@Column(name = "total_distance")
	private Float totalDistance;

	@Column(name = "estimated_duration")
	private Integer estimatedDuration;

	@Column(name = "departure_address", length = 255)
	private String departureAddress;

	@Column(name = "departure_time")
	private LocalDateTime departureTime;

	@Column(name = "map_url", columnDefinition = "TEXT")
	private String mapUrl;
}
