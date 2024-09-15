package com.learning.credit.model;

public class ApplyForCreditCardResponse {

    private final Status status;

    private String uuid;

    public ApplyForCreditCardResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public enum Status {
        GRANTED,
        DENIED
    }
}
