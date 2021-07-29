package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText, passwordText, emailText, passwordText2;
    private TextView txtuser, txtpsw, txtpsw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameText = findViewById(R.id.username_text);
        passwordText = findViewById(R.id.password_text);
        passwordText2 = findViewById(R.id.password_text2);
        emailText = findViewById(R.id.email_text);
        txtuser = findViewById(R.id.textViewUSER);
        txtpsw = findViewById(R.id.textViewPSW1);
        //txtpsw2 = findViewById(R.id.textViewPSW2);
    }

    public void register(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.103:8080/api/users";
        emailText.setError(null);

        if(validation()) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", usernameText.getText().toString());
                jsonObject.put("email", emailText.getText().toString());
                jsonObject.put("password", passwordText.getText().toString());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(RegisterActivity.this, "Registrazione effettuata con successo", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        emailText.setError("Email già in uso");
                    }
                });
                queue.add(jsonObjectRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validation() {
        boolean bool = true;

        if (usernameText.getText().toString().length() < 6 || usernameText.getText().toString().length() > 16 || usernameText.getText().toString().isEmpty()) {
            usernameText.setError("L'username non soddisfa i requisiti");
            bool = false;
        } else if (passwordText.getText().toString().length() < 8 || passwordText.getText().toString().length() > 16 || passwordText.getText().toString().isEmpty()) {
            passwordText.setError("La password non soddisfa i requisiti");
            bool = false;
        } else if (!(passwordText2.getText().toString().equals(passwordText.getText().toString()))) {
            passwordText2.setError("Le password sono diverse");
            bool = false;
        }
        return bool;
    }
}