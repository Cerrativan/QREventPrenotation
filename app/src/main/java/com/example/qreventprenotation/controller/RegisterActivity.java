package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;
import com.example.qreventprenotation.model.User;

import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {

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
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.103:8080/api/users";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", usernameText.getText().toString());
            jsonObject.put("email", emailText.getText().toString());
            jsonObject.put("password", passwordText.getText().toString());

            Thread thread = new Thread() {
                public void run() {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Registrazione effettuata con successo", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.getMessage();
                        }
                    });
                    queue.add(jsonObjectRequest);
                }
            };
            thread.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}