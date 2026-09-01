package com.outing.api.event.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.outing.api.authentication.entities.User;
import com.outing.api.client.entities.Client;
import com.outing.api.client.entities.ServicePrice;
import com.outing.api.event.enums.BookingStatus;
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
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_session_id", nullable = false)
	private EventSession eventSession;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_price_id")
	private ServicePrice servicePrice;

	@Column(name = "price", precision = 6, scale = 2)
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private BookingStatus status = BookingStatus.CONFIRMED;

	@Column(name = "waitlist_position")
	private Integer waitlistPosition;

	@Column(name = "note", length = 500)
	private String note;

	@Column(name = "checked_in_at")
	private LocalDateTime checkedInAt;

	@Column(name = "checked_out_at")
	private LocalDateTime checkedOutAt;

	@Column(name = "absence_reason", length = 100)
	private String absenceReason;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cancelled_by")
	private User cancelledBy;

	@Column(name = "cancellation_reason", length = 255)
	private String cancellationReason;

	@Column(name = "cancelled_at")
	private LocalDateTime cancelledAt;

	@Column(name = "is_short_notice_cancellation", nullable = false)
	private Boolean isShortNoticeCancellation = false;
}