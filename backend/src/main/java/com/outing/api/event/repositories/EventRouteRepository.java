package com.outing.api.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.event.entities.EventRoute;

public interface EventRouteRepository extends JpaRepository<EventRoute, Integer> {

}
