package com.example.qreventprenotation.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.qreventprenotation.R;
import com.example.qreventprenotation.UserService;
import com.example.qreventprenotation.model.User;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private UserService userService;
    private User user;
    private EditText usernameText, passwordText, emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameText = findViewById(R.id.username_text);
        passwordText = findViewById(R.id.password_text);
        emailText = findViewById(R.id.email_text);
    }

    public void register(View view) {
        user = new User(usernameText.getText().toString(), passwordText.getText().toString(),emailText.getText().toString(), UUID.randomUUID());
        //chiamata service per aggiungere nel database
    }
}