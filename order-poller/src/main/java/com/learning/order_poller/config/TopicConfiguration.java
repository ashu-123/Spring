package com.learning.order_poller.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfiguration {

    @Value("${order.poller.topic.name}")
    private String name;

    public String getName() {
        return name;
    }

    @Bean
    public NewTopic newTopic() {
        return new NewTopic(name, 3, (short) 1);
    }
}
