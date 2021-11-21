package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;
import com.example.qreventprenotation.adapter.CustomAdapter;
import com.example.qreventprenotation.json.MyJsonArrayRequest;
import com.example.qreventprenotation.model.Prenotation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrenotationListActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        recyclerView = findViewById(R.id.recycler_prenotazioni);
        textView = findViewById(R.id.text_noprenotazioni);

        textView.setVisibility(View.GONE);

        getPrenotations();


    }

    public void getPrenotations() {

        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.16:8080/api/getprenotations";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userid", id);


            MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        ArrayList<Prenotation> list = new Gson().fromJson(response.toString(), new TypeToken<List<Prenotation>>(){}.getType());
                        if(list.isEmpty()) {
                            textView.setVisibility(View.VISIBLE);
                        }else {
                            initRecycler(list);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(myJsonArrayRequest);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initRecycler(ArrayList<Prenotation> list) {
        adapter = new CustomAdapter(list, PrenotationListActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}