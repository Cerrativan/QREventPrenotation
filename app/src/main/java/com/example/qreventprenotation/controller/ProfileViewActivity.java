package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.qreventprenotation.R;

public class ProfileViewActivity extends AppCompatActivity {

    TextView pswChange;
    Intent openPsWChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        pswChange = findViewById(R.id.textViewChangePsw);
        pswChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPsWChange = new Intent(ProfileViewActivity.this, ChangePasswordActivity.class);
                startActivity(openPsWChange);
            }
        });
    }
}