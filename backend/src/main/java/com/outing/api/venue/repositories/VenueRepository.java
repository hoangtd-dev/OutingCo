package com.outing.api.venue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.venue.entities.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
