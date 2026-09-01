package com.outing.api.event.entities;

import java.time.LocalDateTime;

import com.outing.api.authentication.entities.User;
import com.outing.api.notification.entities.EmailTemplate;
import com.outing.api.notification.enums.EmailStatus;
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
@Table(name = "event_emails")
@Getter
@Setter
@NoArgsConstructor
public class EventEmail extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_session_id", nullable = false)
	private EventSession eventSession;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email_template_id")
	private EmailTemplate emailTemplate;

	@Column(name = "subject", length = 100)
	private String subject;

	@Column(name = "body", columnDefinition = "TEXT")
	private String body;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 10)
	private EmailStatus status = EmailStatus.DRAFT;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@Column(name = "approved_at")
	private LocalDateTime approvedAt;

	@Column(name = "sent_at")
	private LocalDateTime sentAt;
}