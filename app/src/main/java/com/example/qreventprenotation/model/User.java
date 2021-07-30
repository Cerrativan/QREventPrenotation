package com.example.qreventprenotation.model;

import java.util.UUID;

public class User {

    private int id;
    private String nome;
    private String cognome;
    private String password;
    private String email;

    public User(int id, String nome, String cognome, String password, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String username) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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
