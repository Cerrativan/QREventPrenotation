package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class LoginActivity extends AppCompatActivity {

    private EditText emailLogin, passwordLogin;
    private TextView registerText;
    private Intent openMainActivity;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.lgn_email);
        passwordLogin = findViewById(R.id.lgn_password);
        registerText = findViewById(R.id.textView4);
        checkBox = findViewById(R.id.checkBox);

        automaticLogin();

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(openRegister);
            }
        });
    }

    public void login(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.16:8080/api/getusers";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", emailLogin.getText().toString());
            jsonObject.put("password", passwordLogin.getText().toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getString("result").equals("true")) {
                            saveSharedPreferences(response);
                            openMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(openMainActivity);
                        }else {
                            passwordLogin.setError("Password errata");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    emailLogin.setError("Utente non esistente");
                }
            });
            queue.add(jsonObjectRequest);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void automaticLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.getString("login", "").equals("true")) {
            emailLogin.setText(sharedPreferences.getString("email", ""));
            passwordLogin.setText(sharedPreferences.getString("password", ""));

            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
    }

    public void saveSharedPreferences(JSONObject response) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        try {
            if(checkBox.isChecked()) {
                editor.putString("login", "true");
            }else {
                editor.putString("login", "false");
            }
            editor.putString("email", emailLogin.getText().toString());
            editor.putString("password", passwordLogin.getText().toString());
            editor.putString("nome", response.getString("nome"));
            editor.putString("cognome", response.getString("cognome"));
            editor.putString("id", response.getString("userid"));
        }catch (Exception e){
            e.printStackTrace();
        }

        editor.commit();
    }

    @Override
    public void onBackPressed() {
    }
}