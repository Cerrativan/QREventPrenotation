package com.example.qreventprenotation.model;

public class Prenotation {
    private Long prenotationid;
    private Event eventid;
    private User userid;
    private Long prenotation;
    private boolean occupied;


    public Prenotation(Long prenotationid, Event eventid, User userid, Long prenotation, boolean occupied) {
        this.prenotationid = prenotationid;
        this.eventid = eventid;
        this.userid = userid;
        this.prenotation = prenotation;
        this.occupied = occupied;
    }

    public Prenotation(Event eventid, User userid, Long prenotation, boolean occupied) {
        this.eventid = eventid;
        this.userid = userid;
        this.prenotation = prenotation;
        this.occupied = occupied;
    }

    public Long getPrenotationid() {
        return prenotationid;
    }

    public void setPrenotationid(Long prenotationid) {
        this.prenotationid = prenotationid;
    }

    public Event getEventid() {
        return eventid;
    }

    public void setEventid(Event eventid) {
        this.eventid = eventid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
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