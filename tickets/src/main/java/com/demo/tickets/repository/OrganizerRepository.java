package com.demo.tickets.repository;

import com.demo.tickets.model.events.Organizer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizerRepository {

    private final List<Organizer> organizers = List.of(new Organizer(101, "Ashutosh", "Globomantics Technology conference"),
            new Organizer(102, "Anurag", "Carved Rock fitness conference"));

    public List<Organizer> findAll() { return organizers; }
}
