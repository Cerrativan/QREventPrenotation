package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qreventprenotation.R;

public class ProfileViewActivity extends AppCompatActivity {

    TextView pswChange, disconnectTextView, nomeTextView, cognomeTextView, idTextView, emailTextView;
    Intent openPsWChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        pswChange = findViewById(R.id.textViewChangePsw);
        nomeTextView = findViewById(R.id.nomeView);
        cognomeTextView = findViewById(R.id.cognomeView);
        emailTextView = findViewById(R.id.emailView);
        idTextView = findViewById(R.id.idView);
        disconnectTextView = findViewById(R.id.disconnectText);

        getUserData();

        pswChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPsWChange = new Intent(ProfileViewActivity.this, ChangePasswordActivity.class);
                startActivity(openPsWChange);
            }
        });

        disconnectTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeUserData();
                Intent intent = new Intent(ProfileViewActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);

        String email = sharedPreferences.getString("email", "");
        String id = sharedPreferences.getString("id", "");
        String nome = sharedPreferences.getString("nome", "");
        String cognome = sharedPreferences.getString("cognome", "");

        emailTextView.setText(email);
        emailTextView.setPaintFlags(emailTextView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        nomeTextView.setText(nome);
        cognomeTextView.setText(cognome);
        idTextView.setText(id);

    }

    public void removeUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);

        sharedPreferences.edit().clear().commit();
    }
}