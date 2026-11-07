package com.outing.api.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.event.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
