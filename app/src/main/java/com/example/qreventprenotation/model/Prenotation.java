package com.example.qreventprenotation.model;

public class Prenotation {
    private Long id;
    private Long eventId;
    private int userId;
    private Long prenotation;
    private boolean occupied;

    public Prenotation(Long id, Long eventId, int userId, Long prenotation, boolean occupied) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.prenotation = prenotation;
        this.occupied = occupied;
    }

    public Prenotation(Long eventId, int userId, Long prenotation, boolean occupied) {
        this.eventId = eventId;
        this.userId = userId;
        this.prenotation = prenotation;
        this.occupied = occupied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getPrenotation() {
        return prenotation;
    }

    public void setPrenotation(Long prenotation) {
        this.prenotation = prenotation;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}