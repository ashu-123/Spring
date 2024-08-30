package com.learning.flights.service;

import com.learning.flights.domain.Aircraft;
import com.learning.flights.domain.FlightPlan;
import com.learning.flights.domain.WakeTurbulence;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public FlightPlan findById(String id) {
        return mongoOperations.findById(id, FlightPlan.class);
    }

    public List<FlightPlan> findByInternationalAndCrossingAmausi() {
        var isInternational = Criteria.where("isInternational").is(true);
        var crossingFrance = Criteria.where("crossedCountries").in("Amausi");
        var criteria = new Criteria().andOperator(isInternational, crossingFrance);

        var query = new Query();
        query.addCriteria(criteria);

        return mongoOperations.find(query, FlightPlan.class);
    }

    public List<FlightPlan> findByFirstTwoFlightsWhichLastBetweenOneAndThreeHours() {
        var criteria = Criteria.where("flightDuration").gte(60).lte(180);
        var query = new Query(criteria).with(PageRequest.of(0, 2));
        return mongoOperations.find(query, FlightPlan.class);
    }

    public List<FlightPlan> findByLucknowFlightsAndOrderBySeatCapacity() {
        var criteria = Criteria.where("destinationCity").regex("Lucknow");
        var query = new Query(criteria).with(Sort.by("aircraft.capacity").descending());

        return mongoOperations.find(query, FlightPlan.class);
    }
}
