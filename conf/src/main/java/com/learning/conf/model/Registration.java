package com.learning.conf.model;

import jakarta.validation.constraints.NotEmpty;

public class Registration {

    @NotEmpty(message = "Name can't be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
