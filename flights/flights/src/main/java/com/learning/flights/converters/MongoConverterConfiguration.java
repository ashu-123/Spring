package com.learning.flights.converters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;

@Configuration
public class MongoConverterConfiguration {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        var converters = new ArrayList<>();
        converters.add(new AircraftWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
