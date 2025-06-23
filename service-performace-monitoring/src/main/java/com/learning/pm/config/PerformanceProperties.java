package com.learning.pm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "performance")
public class PerformanceProperties {
    private long thresholdMs;

    public long getThresholdMs() {
        return thresholdMs;
    }

    public void setThresholdMs(long thresholdMs) {
        this.thresholdMs = thresholdMs;
    }
}

