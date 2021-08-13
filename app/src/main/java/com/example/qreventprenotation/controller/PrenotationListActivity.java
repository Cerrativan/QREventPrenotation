package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;
import com.example.qreventprenotation.json.MyJsonArrayRequest;
import com.example.qreventprenotation.model.Prenotation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrenotationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);


        getPrenotations();


    }

    public void getPrenotations() {

        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.128:8080/api/getprenotations";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userid", id);

            MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        Gson gson = new Gson();
                        /*JSONObject jsonResponse = response.getJSONObject(1);
                        if(jsonResponse.getString("prenotation").equals("1"))
                            Toast.makeText(PrenotationListActivity.this, jsonResponse.get("userid").toString(), Toast.LENGTH_SHORT).show();*/
                        ArrayList<Prenotation> list = new Gson().fromJson(response.toString(), new TypeToken<List<Prenotation>>(){}.getType());
                        Toast.makeText(PrenotationListActivity.this, list.get(1).getEventid().getEventid().toString(), Toast.LENGTH_SHORT).show();
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
}