package com.learning.flights.service;

import com.learning.flights.domain.Aircraft;
import com.learning.flights.domain.FlightPlan;
import com.learning.flights.domain.WakeTurbulence;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightPlanRepositoryDataService implements FlightPlanDataService{

    private final FlightPlanRepository flightPlanRepository;

    public FlightPlanRepositoryDataService(FlightPlanRepository flightPlanRepository) {
        this.flightPlanRepository = flightPlanRepository;
    }


    @Override
    public void initializeFlightPlans() {
        var flight1 = new FlightPlan("Kanpur",
                "Lucknow",
                LocalDateTime.now(),
                5000,
                List.of("Amausi"),
                true,
                new Aircraft("MT-10", 400, WakeTurbulence.Light));

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
                new Aircraft("MT-10", 400, WakeTurbulence.Medium));

        this.flightPlanRepository.insert(flight1);
        this.flightPlanRepository.insert(List.of(flight2, flight3));
    }

    @Override
    public FlightPlan findById(String id) {
        return null;
    }

    @Override
    public List<FlightPlan> findByInternationalAndCrossingAmausi() {
        return null;
    }

    @Override
    public List<FlightPlan> findByFirstTwoFlightsWhichLastBetweenOneAndThreeHours() {
        return null;
    }

    @Override
    public List<FlightPlan> findByLucknowFlightsAndOrderBySeatCapacity() {
        return null;
    }

    @Override
    public UpdateResult updateAircraftCapacity(int newCapacity) {
        return null;
    }

    @Override
    public DeleteResult deleteDocuments() {
        return null;
    }
}
