package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private EditText nomeText, cognomeText, passwordText, emailText, passwordText2;
    private TextView txtpsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nomeText = findViewById(R.id.nome_text);
        cognomeText = findViewById(R.id.cognome_text);
        passwordText = findViewById(R.id.password_text);
        passwordText2 = findViewById(R.id.password_text2);
        emailText = findViewById(R.id.email_text);
        txtpsw = findViewById(R.id.textViewPSW1);
    }

    public void register(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.103:8080/api/postusers";
        emailText.setError(null);

        if(validation()) {
            try {
                String hash = BCrypt.hashpw(passwordText.getText().toString(), BCrypt.gensalt());

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("nome", nomeText.getText().toString());
                jsonObject.put("cognome", cognomeText.getText().toString());
                jsonObject.put("email", emailText.getText().toString());
                jsonObject.put("password", hash);

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

    protected boolean validation() {
        boolean bool = true;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailText.getText().toString());

        if(!(matcher.matches())) {
            emailText.setError("L'email inserita non è valida");
            bool = false;
        }
        if (passwordText.getText().toString().length() < 8 || passwordText.getText().toString().length() > 16 || passwordText.getText().toString().isEmpty()) {
            passwordText.setError("La password non soddisfa i requisiti");
            bool = false;
        } else if (!(passwordText2.getText().toString().equals(passwordText.getText().toString()))) {
            passwordText2.setError("Le password sono diverse");
            bool = false;
        }
        return bool;
    }
}