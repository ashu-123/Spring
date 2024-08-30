package com.learning.flights;

import com.learning.flights.domain.Aircraft;
import com.learning.flights.domain.FlightPlan;
import com.learning.flights.domain.WakeTurbulence;
import com.learning.flights.service.AircraftRepository;
import com.learning.flights.service.FlightPlanDataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/*
This component will be executed by Spring Framework immediately after application
bootstrap
 */
@Component
public class MainRunner implements CommandLineRunner {
    private final FlightPlanDataService flightPlanDataService;


    private final AircraftRepository aircraftRepository;

    public MainRunner(@Qualifier("flightPlanRepositoryDataService") FlightPlanDataService flightPlanDataService, AircraftRepository aircraftRepository) {
        this.flightPlanDataService = flightPlanDataService;
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // create
//        this.flightPlanDataService.initializeFlightPlans();

        //this.flightPlanDataService.initializeFlightPlans();

        // read (query)
        //System.out.println(this.flightPlanDataService.findById("63e383b9b64a16763c50e5c8"));
        //System.out.println(this.flightPlanDataService.findByInternationalAndCrossingAmausi());
        //System.out.println(this.flightPlanDataService.findByFirstTwoFlightsWhichLastBetweenOneAndThreeHours());
        //System.out.println(this.flightPlanDataService.findByLucknowFlightsAndOrderBySeatCapacity());
        //System.out.println(this.flightPlanDataService.findByFullTextSearch("France"));

        // update

//        System.out.println(this.flightPlanDataService.updateAircraftCapacity(50000));
        /*
        this.flightPlanDataService.incrementDepartureTime(
                "63e383b9b64a16763c50e5c8",
                LocalDateTime.of(2024,1,1,20,0)
        );
         */

        //this.flightPlanDataService.updateAircraftCapacity(7000);

        // delete
        //flightPlanDataService.deleteById("63e383b9b64a16763c50e5c8");
        //flightPlanDataService.deleteAllFromParis();
        //flightPlanDataService.deleteAll();
        //System.out.println(this.flightPlanDataService.deleteDocuments());


        /////////////////////////////////////////////////////////////

        var aircraft = new Aircraft("ET-01", 700, WakeTurbulence.Heavy);
        this.aircraftRepository.insert(aircraft);

        var f1 = new FlightPlan("Kochi",
                "Bengaluru",
                LocalDateTime.now(),
                5000,
                List.of("Amausi"),
                true,
                aircraft);
        this.flightPlanDataService.insert(f1);

    }


}
