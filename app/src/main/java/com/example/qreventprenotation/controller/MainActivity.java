package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qreventprenotation.R;

public class MainActivity extends AppCompatActivity {

    Button eventPrenotation, eventList, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventPrenotation = findViewById(R.id.btn_eventPrenotation);
        eventList = findViewById(R.id.btn_eventList);
        profile = findViewById(R.id.btn_profile);

        eventPrenotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openEventPrenotation = new Intent(getApplicationContext(), EventPrenotationActivity.class);
                startActivity(openEventPrenotation);
            }
        });

        eventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openEventList = new Intent(getApplicationContext(), PrenotationListActivity.class);
                startActivity(openEventList);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openProfile = new Intent(getApplicationContext(), ProfileViewActivity.class);
                startActivity(openProfile);
            }
        });
    }
}