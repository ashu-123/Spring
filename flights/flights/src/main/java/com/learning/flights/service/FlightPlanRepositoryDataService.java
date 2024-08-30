package com.learning.flights.service;

import com.learning.flights.domain.Aircraft;
import com.learning.flights.domain.FlightPlan;
import com.learning.flights.domain.WakeTurbulence;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightPlanRepositoryDataService implements FlightPlanDataService{

    private final FlightPlanRepository flightPlanRepository;

    public FlightPlanRepositoryDataService(FlightPlanRepository flightPlanRepository) {
        this.flightPlanRepository = flightPlanRepository;
    }

    public void insert(FlightPlan flightPlan) {
        this.flightPlanRepository.insert(flightPlan);
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
        return this.flightPlanRepository.findById(id).orElse(null);
    }

    @Override
    public List<FlightPlan> findByInternationalAndCrossingAmausi() {
        return this.flightPlanRepository.findByInternationalAndCrossingAmausi();
    }

    @Override
    public List<FlightPlan> findByFirstTwoFlightsWhichLastBetweenOneAndThreeHours() {
        return this.flightPlanRepository.findByFlightDurationBetween(60, 180, PageRequest.of(0, 2));
    }

    @Override
    public List<FlightPlan> findByLucknowFlightsAndOrderBySeatCapacity() {
        return this.flightPlanRepository.findByAircraftModelLikeOrderByAircraftSeatCapacity("MT-10");
    }

    @Override
    public void updateAircraftCapacity(int newCapacity) {
         this.flightPlanRepository.findAndUpdateAircraftCapacityById("66d1812961ef0f26af75e46c", newCapacity);
    }

    @Override
    public Long deleteDocuments() {
        return this.flightPlanRepository.deleteByCrossedCountriesIn("AAmausi");
    }
}
