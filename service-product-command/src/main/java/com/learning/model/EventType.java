package com.learning.model;

public enum EventType {

    PRODUCT_CREATED("PRODUCT-CREATED"), PRODUCT_UPDATED("PRODUCT_UPDATED");

    private final String eventType;

    EventType(String eventType) { this.eventType = eventType; }

    public String getEventType() { return eventType; }
}
