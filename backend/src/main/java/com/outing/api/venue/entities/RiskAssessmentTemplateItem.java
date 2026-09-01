package com.outing.api.venue.entities;

import java.math.BigDecimal;

import com.outing.api.shared.entities.SoftDeleteEntity;
import com.outing.api.venue.enums.TemplateItemType;

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
@Table(name = "risk_assessment_template_items")
@Getter
@Setter
@NoArgsConstructor
public class RiskAssessmentTemplateItem extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", nullable = false)
	private RiskAssessmentTemplate template;

	@Column(name = "question", nullable = false, length = 200)
	private String question;

	@Column(name = "description", length = 500)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, length = 20)
	private TemplateItemType type = TemplateItemType.YES_NO;

	@Column(name = "options", columnDefinition = "JSON")
	private String options;

	@Column(name = "weight", nullable = false, precision = 5, scale = 2)
	private BigDecimal weight = BigDecimal.ONE;

	@Column(name = "is_required", nullable = false)
	private Boolean isRequired = true;

	@Column(name = "is_booking", nullable = false)
	private Boolean isBooking = false;

	@Column(name = "order_index", nullable = false)
	private Integer orderIndex = 0;

	@Column(name = "category", length = 60)
	private String category;
}
