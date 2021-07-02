package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qreventprenotation.R;

public class MainActivity extends AppCompatActivity {

    Button eventPrenotation, eventList, profile;
    Intent openEventPrenotation, openEventList, openProfile;

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
                openEventPrenotation = new Intent(MainActivity.this, EventPrenotationActivity.class);
                startActivity(openEventPrenotation);
            }
        });

        eventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chiamata activity eventi prenotati
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chiamata pagina profilo
            }
        });
    }
}