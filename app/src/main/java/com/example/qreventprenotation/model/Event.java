package com.example.qreventprenotation.model;

public class Event {
    private Long eventid;
    private String nome;
    private String location;
    private String maxprenotation;
    private String date;
    private String hour;

    public Event(Long eventid, String nome, String location, String maxprenotation, String date, String hour) {
        this.eventid = eventid;
        this.nome = nome;
        this.location = location;
        this.maxprenotation = maxprenotation;
        this.date = date;
        this.hour = hour;
    }

    public Event(String nome, String location, String maxprenotation, String date, String hour) {
        this.nome = nome;
        this.location = location;
        this.maxprenotation = maxprenotation;
        this.date = date;
        this.hour = hour;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxprenotation() {
        return maxprenotation;
    }

    public void setMaxprenotation(String maxprenotation) {
        this.maxprenotation = maxprenotation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
