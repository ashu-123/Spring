package com.learning.creditCheckService.model;

import java.util.UUID;

public class CreditCheckResponse {

    private Score score;

    private String uuid = UUID.randomUUID().toString();

    public CreditCheckResponse(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public enum Score{
        HIGH, LOW
    }
}
