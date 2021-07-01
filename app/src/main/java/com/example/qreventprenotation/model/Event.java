package com.example.qreventprenotation.model;

import java.util.UUID;

public class Event {
    private String name;
    private UUID id;
    private String location;
    private String maxPrenotations;

    public Event(String name, UUID id, String location, String maxPrenotations) {
        this.name = name;
        this.id = id;
        this.location = location;
        this.maxPrenotations = maxPrenotations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxPrenotations() {
        return maxPrenotations;
    }

    public void setMaxPrenotations(String maxPrenotations) {
        this.maxPrenotations = maxPrenotations;
    }
}
