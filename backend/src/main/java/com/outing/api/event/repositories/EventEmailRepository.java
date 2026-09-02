package com.outing.api.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.event.entities.EventEmail;

public interface EventEmailRepository extends JpaRepository<EventEmail, Integer> {

}
