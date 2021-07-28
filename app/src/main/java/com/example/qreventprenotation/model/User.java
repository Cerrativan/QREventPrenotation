package com.example.qreventprenotation.model;

import java.util.UUID;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;

    public User(int id, String nickname, String password, String email) {
        this.id = id;
        this.username = nickname;
        this.password = password;
        this.email = email;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
