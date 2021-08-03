package com.example.qreventprenotation.model;

public class Prenotation {
    private Long id;
    private Long eventId;
    private int userId;

    public Prenotation(Long id, Long eventId, int userId) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
    }

    public Prenotation(Long eventId, int userId) {
        this.eventId = eventId;
        this.userId = userId;
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
}
