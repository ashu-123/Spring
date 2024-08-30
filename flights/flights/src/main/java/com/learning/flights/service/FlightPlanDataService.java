package com.learning.flights.service;

import com.learning.flights.domain.Aircraft;
import com.learning.flights.domain.FlightPlan;
import com.learning.flights.domain.WakeTurbulence;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightPlanDataService {

    private final MongoOperations mongoOperations;

    public FlightPlanDataService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public void initializeFlightPlans() {

        var flight1 = new FlightPlan("Kanpur",
                "Lucknow",
                LocalDateTime.now(),
                5000,
                List.of("Amausi"),
                true,
                new Aircraft("MT-10", 400, WakeTurbulence.Heavy));

        var flight2 = new FlightPlan("Mumbai",
                "Bengaluru",
                LocalDateTime.now(),
                5000,
                List.of("Amausi"),
                true,
                new Aircraft("MT-10", 400, WakeTurbulence.Heavy));

        var flight3= new FlightPlan("Lucknow",
                "Kanpur",
                LocalDateTime.now(),
                5000,
                List.of("Amausi"),
                true,
                new Aircraft("MT-10", 400, WakeTurbulence.Heavy));

        var flights = List.of(flight2, flight3);
        mongoOperations.insert(flight1);
        mongoOperations.insert(flights, FlightPlan.class);
    }
}
