package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = findViewById(R.id.lgn_email);
        passwordLogin = findViewById(R.id.lgn_password);
        registerText = findViewById(R.id.textView4);


        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(openRegister);
            }
        });
    }

    public void login(View view) {
        //BCrypt.checkpw(PWD_INSERITA_UTENTE, HASH_SALVATO_SU_DB
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.103:8080/api/users";

        try {
            String hash = BCrypt.hashpw(passwordLogin.getText().toString(), BCrypt.gensalt());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", emailLogin.getText().toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String psw = null;
                    try {
                        psw = response.getString("password");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(BCrypt.checkpw(hash, psw)) {
                        Toast.makeText(LoginActivity.this,"Login eseguito", Toast.LENGTH_SHORT).show();
                        openMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(openMainActivity);
                    }else {
                        passwordLogin.setError("Password errata");
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
}