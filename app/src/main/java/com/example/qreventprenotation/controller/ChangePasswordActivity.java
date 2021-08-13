package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;

import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText oldPswText, newPswText, confirmNewPswText;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPswText = findViewById(R.id.editTextOldPsw);
        newPswText = findViewById(R.id.editTextNewPsw);
        confirmNewPswText = findViewById(R.id.editTextConfirmPsw);
        update = findViewById(R.id.updateBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });

    }

    public void updatePassword() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.128:8080/api/putusers";
        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);

        if(validation()) {
            try {
                String hash = BCrypt.hashpw(newPswText.getText().toString(), BCrypt.gensalt());

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", sharedPreferences.getString("email", ""));
                jsonObject.put("password", oldPswText.getText().toString());
                jsonObject.put("newpassword", hash);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Password aggiornata con successo", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        oldPswText.setError("Password errata");
                    }
                });
                queue.add(jsonObjectRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean validation() {
        boolean bool = true;

        if (newPswText.getText().toString().length() < 8 || newPswText.getText().toString().length() > 16 || newPswText.getText().toString().isEmpty()) {
            newPswText.setError("La password non soddisfa i requisiti");
            bool = false;
        } else if (!(confirmNewPswText.getText().toString().equals(newPswText.getText().toString()))) {
            confirmNewPswText.setError("Le password sono diverse");
            bool = false;
        }
        return bool;
    }
}