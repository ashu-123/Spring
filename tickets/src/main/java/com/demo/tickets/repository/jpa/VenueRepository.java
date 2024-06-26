package com.demo.tickets.repository.jpa;

import com.demo.tickets.model.jpa.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
}
