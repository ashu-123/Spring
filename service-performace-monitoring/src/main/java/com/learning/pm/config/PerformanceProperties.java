package com.learning.pm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "performance")
public class PerformanceProperties {
    private long thresholdMs;
    private int maxStringLength = 100;
    private int maxCollectionSize = 10;
    private int maxMapSize = 10;


    public long getThresholdMs() {
        return thresholdMs;
    }

    public void setThresholdMs(long thresholdMs) {
        this.thresholdMs = thresholdMs;
    }

    public int getMaxStringLength() {
        return maxStringLength;
    }

    public PerformanceProperties setMaxStringLength(int maxStringLength) {
        this.maxStringLength = maxStringLength;
        return this;
    }

    public int getMaxCollectionSize() {
        return maxCollectionSize;
    }

    public PerformanceProperties setMaxCollectionSize(int maxCollectionSize) {
        this.maxCollectionSize = maxCollectionSize;
        return this;
    }

    public int getMaxMapSize() {
        return maxMapSize;
    }

    public PerformanceProperties setMaxMapSize(int maxMapSize) {
        this.maxMapSize = maxMapSize;
        return this;
    }
}

