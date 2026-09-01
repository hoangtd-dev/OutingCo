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
@Table(name = "risk_assessment_answers")
@Getter
@Setter
@NoArgsConstructor
public class RiskAssessmentAnswer extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assessment_id", nullable = false)
	private RiskAssessment assessment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_item_id", nullable = false)
	private RiskAssessmentTemplateItem templateItem;

	@Column(name = "answer_value", nullable = false, columnDefinition = "JSON")
	private String answerValue;
}
