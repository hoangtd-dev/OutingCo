package com.outing.api.venue.entities;

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
@Table(name = "risk_assessment_templates")
@Getter
@Setter
@NoArgsConstructor
public class RiskAssessmentTemplate extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "is_global", nullable = false)
	private Boolean isGlobal = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_template_id")
	private RiskAssessmentTemplate parentTemplate;

	@Column(name = "version", nullable = false)
	private Integer version = 1;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;
}
