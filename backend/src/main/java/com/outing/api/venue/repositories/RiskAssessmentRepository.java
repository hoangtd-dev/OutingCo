package com.outing.api.venue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.venue.entities.RiskAssessment;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Integer> {

}
