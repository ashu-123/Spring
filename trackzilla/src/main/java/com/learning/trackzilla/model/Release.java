package com.learning.trackzilla.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document
public class Release {

    @Id
    private String id;
    private String name;
    private String description;
    private List<Ticket> tickets;
    private ZonedDateTime releaseDate;

    @Transient
    private Double estimatedCosts;

    public Release() {

    }

    public Release(String name, String description, List<Ticket> tickets, ZonedDateTime releaseDate, Double estimatedCosts) {
        this.name = name;
        this.description = description;
        this.tickets = tickets;
        this.releaseDate = releaseDate;
        this.estimatedCosts = estimatedCosts;
    }

    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Double getEstimatedCosts() { return this.estimatedCosts; }

    public void setEstimatedCosts(Double estimatedCosts) { this.estimatedCosts = estimatedCosts; }

}
