package com.learning.flights.service;

import com.learning.flights.domain.Aircraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends MongoRepository<Aircraft, String> {
}
