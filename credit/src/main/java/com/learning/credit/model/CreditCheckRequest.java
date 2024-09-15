package com.learning.credit.model;

import java.time.LocalDate;
import java.util.UUID;

public class CreditCheckRequest {

    private int citizenNumber;

    private String requestedDate = LocalDate.now().toString();

    private String uuid = UUID.randomUUID().toString();

    public CreditCheckRequest(int citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public int getCitizenNumber() {
        return citizenNumber;
    }

    public void setCitizenNumber(int citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
