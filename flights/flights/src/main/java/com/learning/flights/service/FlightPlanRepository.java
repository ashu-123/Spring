package com.learning.flights.service;

import com.learning.flights.domain.FlightPlan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightPlanRepository extends MongoRepository<FlightPlan, String> {

    public List<FlightPlan> findByFlightDurationBetween(int minDuration, int maxDuration, PageRequest pageRequest);

    public List<FlightPlan> findByAircraftModelLikeOrderByAircraftSeatCapacity(String model);

    @Query("{'isInternational' : true, 'crossedCountries' : { '$in' : ['Amausi']}}")
    public List<FlightPlan> findByInternationalAndCrossingAmausi();

    @Update("{'$set' : { 'aircraft.capacity' : ?1 }}")
    public void findAndUpdateAircraftCapacityById(String id, int newCapacity);
}
