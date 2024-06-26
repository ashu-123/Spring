package com.demo.tickets.repository.jpa;

import com.demo.tickets.model.jpa.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}
