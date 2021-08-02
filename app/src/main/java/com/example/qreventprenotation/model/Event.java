package com.example.qreventprenotation.model;

public class Event {
    private Long id;
    private String name;
    private String location;
    private String maxPrenotation;

    public Event(Long id, String name, String location, String maxPrenotation) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.maxPrenotation = maxPrenotation;
    }

    public Event(String name, String location, String maxPrenotation) {
        this.name = name;
        this.location = location;
        this.maxPrenotation = maxPrenotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxPrenotation() {
        return maxPrenotation;
    }

    public void setMaxPrenotation(String maxPrenotation) {
        this.maxPrenotation = maxPrenotation;
    }
}
