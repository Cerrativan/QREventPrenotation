package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qreventprenotation.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameLogin, passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLogin = findViewById(R.id.lgn_username);
        passwordLogin = findViewById(R.id.lgn_password);

        TextView txtvw = (TextView) findViewById(R.id.textView4);
        txtvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(openRegister);
            }
        });
    }

    public void login(View view) {
        //query database
    }
}