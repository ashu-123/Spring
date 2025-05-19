package com.learning.model.doctor;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doctor {

    @Id
    private int id;
    private String name;

    public Doctor() {
    }

    public Doctor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Doctor setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Doctor setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Doctor{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
