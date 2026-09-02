package com.outing.api.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.event.entities.EventSession;

public interface EventSessionRepository extends JpaRepository<EventSession, Integer> {

}
