package com.outing.api.event.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.outing.api.event.enums.SessionStatus;
import com.outing.api.shared.entities.SoftDeleteEntity;
import com.outing.api.venue.entities.Venue;

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
@Table(name = "event_sessions")
@Getter
@Setter
@NoArgsConstructor
public class EventSession extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venue_id")
	private Venue venue;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "event_date", nullable = false)
	private LocalDate eventDate;

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;

	@Column(name = "max_participant", nullable = false)
	private Integer maxParticipant;

	@Column(name = "registration_token", nullable = false, unique = true, length = 64)
	private String registrationToken;

	@Column(name = "registration_closes_at")
	private LocalDateTime registrationClosesAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private SessionStatus status = SessionStatus.DRAFT;

	@Column(name = "actual_start_at")
	private LocalDateTime actualStartAt;

	@Column(name = "actual_end_at")
	private LocalDateTime actualEndAt;
}