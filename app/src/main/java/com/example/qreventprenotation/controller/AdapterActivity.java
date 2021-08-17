package com.example.qreventprenotation.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qreventprenotation.R;

public class AdapterActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private RelativeLayout relativeLayout;
    private Button button;
    private PopupWindow popupWindow;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        activity = AdapterActivity.this;
        relativeLayout = findViewById(R.id.adapter_layout);
        button = findViewById(R.id.button_cancella);
        textView = findViewById(R.id.text_chiudi);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.popup_layout, null);

        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deletePrenotation();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}