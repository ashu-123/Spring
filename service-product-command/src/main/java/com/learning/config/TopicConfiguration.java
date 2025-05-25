package com.learning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfiguration {

    @Value("${topic.name}")
    private String name;

    public String getName() { return name; }
}
