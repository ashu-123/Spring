package com.learning.flights.service;

import com.learning.flights.domain.Aircraft;
import com.learning.flights.domain.FlightPlan;
import com.learning.flights.domain.WakeTurbulence;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightPlanTemplateDataService implements FlightPlanDataService {

    private final MongoOperations mongoOperations;

    public FlightPlanTemplateDataService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
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

    @Override
    public void insert(FlightPlan flightPlan) {
        this.mongoOperations.insert(flightPlan);
    }

    @Override
    public FlightPlan findById(String id) {
        return mongoOperations.findById(id, FlightPlan.class);
    }

    @Override
    public List<FlightPlan> findByInternationalAndCrossingAmausi() {
        var isInternational = Criteria.where("isInternational").is(true);
        var crossingFrance = Criteria.where("crossedCountries").in("Amausi");
        var criteria = new Criteria().andOperator(isInternational, crossingFrance);

        var query = new Query();
        query.addCriteria(criteria);

        return mongoOperations.find(query, FlightPlan.class);
    }

    @Override
    public List<FlightPlan> findByFirstTwoFlightsWhichLastBetweenOneAndThreeHours() {
        var criteria = Criteria.where("flightDuration").gte(60).lte(180);
        var query = new Query(criteria).with(PageRequest.of(0, 2));
        return mongoOperations.find(query, FlightPlan.class);
    }

    @Override
    public List<FlightPlan> findByLucknowFlightsAndOrderBySeatCapacity() {
        var criteria = Criteria.where("destinationCity").regex("Lucknow");
        var query = new Query(criteria).with(Sort.by("aircraft.capacity").descending());

        return mongoOperations.find(query, FlightPlan.class);
    }

    @Override
    public void updateAircraftCapacity(int newCapacity) {
        var criteria = Criteria.where("id").is("66d1812961ef0f26af75e46c");
        var updateOp = Update.update("aircraft.capacity", newCapacity);
        mongoOperations.updateFirst(new Query(criteria), updateOp, FlightPlan.class);
    }

    @Override
    public Long deleteDocuments() {
        var criteria = Criteria.where("id").in("66d1817639509261bc2f0fb4", "66d1817639509261bc2f0fb3");

        var query = new Query(criteria);
        mongoOperations.remove(query, FlightPlan.class);
        return 1L;
    }
}
