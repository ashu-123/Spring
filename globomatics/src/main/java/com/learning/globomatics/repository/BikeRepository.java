package com.learning.globomatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.globomatics.model.Bike;

public interface BikeRepository extends JpaRepository<Bike, Long> {

}
