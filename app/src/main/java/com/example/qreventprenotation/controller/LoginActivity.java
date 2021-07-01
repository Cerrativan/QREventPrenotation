package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.qreventprenotation.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameLogin, passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLogin = findViewById(R.id.lgn_username);
        passwordLogin = findViewById(R.id.lgn_password);
    }

    public void login(View view) {
        //query nel database
    }
}