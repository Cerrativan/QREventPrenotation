package com.example.qreventprenotation.model;

public class User {

    private int userid;
    private String nome;
    private String cognome;
    private String password;
    private String email;

    public User(int userid, String nome, String cognome, String password, String email) {
        this.userid = userid;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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
}
