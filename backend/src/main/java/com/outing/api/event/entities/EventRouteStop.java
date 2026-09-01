package com.outing.api.event.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.outing.api.client.entities.Client;
import com.outing.api.shared.entities.SoftDeleteEntity;
import com.outing.api.transport.enums.StopStatus;

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
@Table(name = "event_route_stops")
@Getter
@Setter
@NoArgsConstructor
public class EventRouteStop extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "route_id", nullable = false)
	private EventRoute route;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@Column(name = "latitude", precision = 10, scale = 7)
	private BigDecimal latitude;

	@Column(name = "longitude", precision = 10, scale = 7)
	private BigDecimal longitude;

	@Column(name = "stop_order", nullable = false)
	private Integer stopOrder = 0;

	@Column(name = "estimated_pickup_at")
	private LocalDateTime estimatedPickupAt;

	@Column(name = "actual_pickup_at")
	private LocalDateTime actualPickupAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 10)
	private StopStatus status = StopStatus.PENDING;

	@Column(name = "note", length = 255)
	private String note;
}