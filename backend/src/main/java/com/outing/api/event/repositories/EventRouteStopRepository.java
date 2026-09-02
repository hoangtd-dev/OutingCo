package com.outing.api.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.event.entities.EventRouteStop;

public interface EventRouteStopRepository extends JpaRepository<EventRouteStop, Integer> {

}
