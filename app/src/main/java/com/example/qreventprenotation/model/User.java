package com.example.qreventprenotation.model;

import java.util.UUID;

public class User {

    private String username;
    private String password;
    private String email;
    private UUID id;

    public User(String nickname, String password, String email, UUID id) {
        this.username = nickname;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
