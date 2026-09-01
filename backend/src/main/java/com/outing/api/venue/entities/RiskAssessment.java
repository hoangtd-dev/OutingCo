package com.outing.api.venue.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.outing.api.shared.entities.SoftDeleteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "risk_assessments")
@Getter
@Setter
@NoArgsConstructor
public class RiskAssessment extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venue_id", nullable = false)
	private Venue venue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", nullable = false)
	private RiskAssessmentTemplate template;

	@Column(name = "outingco_ref_id")
	private Integer outingcoRefId;

	@Column(name = "risk_score", nullable = false, precision = 5, scale = 2)
	private BigDecimal riskScore;

	@Column(name = "expert_analysis", columnDefinition = "TEXT")
	private String expertAnalysis;

	@Column(name = "published_at")
	private LocalDateTime publishedAt;

	@Column(name = "valid_until")
	private LocalDate validUntil;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;
}
