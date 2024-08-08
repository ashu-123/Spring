package com.learning.trackzilla.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@EnableMongoHttpSession
@Configuration
public class HttpSessionConfig {

    @Bean
    JacksonMongoSessionConverter mongoSessionConverter() {
        return new JacksonMongoSessionConverter();
    }

}
