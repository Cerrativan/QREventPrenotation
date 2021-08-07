package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;

public class EventListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);


    }

    public void getEvents() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.103:8080/api/getprenotations";

    }

    public void init() {
        getEvents();
        TableLayout tbl = (TableLayout) findViewById(R.id.tableLayout);
        TableRow tbr = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText("Nome");
        tv0.setTextColor(Color.BLACK);
        tbr.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Luogo");
        tv1.setTextColor(Color.BLACK);
        tbr.addView(tv1);
        TextView tv2 = new TextView(this);
        tv0.setText("Data");
        tv0.setTextColor(Color.BLACK);
        tbr.addView(tv2);
        TextView tv3 = new TextView(this);
        tv0.setText("Ora");
        tv0.setTextColor(Color.BLACK);
        tbr.addView(tv3);
        TextView tv4 = new TextView(this);
        tv0.setText("Posto");
        tv0.setTextColor(Color.BLACK);
        tbr.addView(tv4);

        tbl.addView(tbr);
    }
}