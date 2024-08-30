package com.learning.flights.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.index.IndexDirection.*;

@Document("FlightPlans")
public class FlightPlan {
    @Id
    private String id;
    @Field("departure")
    private String departureCity;
    @Field("destination")
    private String destinationCity;
    @Indexed(direction = ASCENDING)
    private LocalDateTime departureDateTime;
    private int flightDuration;
    private List<String> crossedCountries;
    private boolean isInternational;
    @DBRef
    private Aircraft aircraft;

    public FlightPlan(String departureCity,
                      String destinationCity,
                      LocalDateTime departureDateTime,
                      int flightDuration,
                      List<String> crossedCountries,
                      boolean isInternational,
                      Aircraft aircraft){
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDateTime = departureDateTime;
        this.flightDuration = flightDuration;
        this.crossedCountries = crossedCountries;
        this.isInternational = isInternational;
        this.aircraft = aircraft;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public List<String> getCrossedCountries() {
        return crossedCountries;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public String toString() {
        return "\nFlightPlan {" +
                "\n id='" + id + '\'' +
                ", \n departure='" + departureCity + '\'' +
                ", \n destination='" + destinationCity + '\'' +
                ", \n departsAt=" + departureDateTime +
                ", \n duration=" + flightDuration +
                ", \n crossedCountries=" + crossedCountries +
                ", \n isInternational=" + isInternational +
                ", \n aircraft=" + aircraft +
                "\n" +
                '}';
    }
}
