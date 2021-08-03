package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qreventprenotation.R;

public class ProfileViewActivity extends AppCompatActivity {

    TextView pswChange;
    Intent openPsWChange;
    EditText username_Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        pswChange = findViewById(R.id.textViewChangePsw);
        username_Profile = findViewById(R.id.usernameProfile);

        getUserData();

        pswChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPsWChange = new Intent(ProfileViewActivity.this, ChangePasswordActivity.class);
                startActivity(openPsWChange);
            }
        });
    }

    public void getUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

        username_Profile.setText(email);

    }
}