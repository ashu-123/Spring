package com.learning.flights.service;

import com.learning.flights.domain.FlightPlan;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface FlightPlanDataService {
    void initializeFlightPlans();

    FlightPlan findById(String id);

    List<FlightPlan> findByInternationalAndCrossingAmausi();

    List<FlightPlan> findByFirstTwoFlightsWhichLastBetweenOneAndThreeHours();

    List<FlightPlan> findByLucknowFlightsAndOrderBySeatCapacity();

    void updateAircraftCapacity(int newCapacity);

    Long deleteDocuments();
}
