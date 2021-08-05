package com.example.qreventprenotation.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Size;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;
import com.example.qreventprenotation.qr.QRCodeFoundListener;
import com.example.qreventprenotation.qr.QRCodeImageAnalyzer;
import com.google.common.util.concurrent.ListenableFuture;

import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class EventPrenotationActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 10;
    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_prenotation);

        previewView = findViewById(R.id.activity_prenotation_previewView);

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permission == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        }else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMERA) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            }else {
                Toast.makeText(this, "Permessi rifiutati", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startCamera() {
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                ImageAnalysis imageAnalysis =
                        new ImageAnalysis.Builder()
                                .setTargetResolution(new Size(1280, 720))
                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                .build();

                imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new QRCodeImageAnalyzer(new QRCodeFoundListener() {
                    @Override
                    public void onQRCodeFound(String qrCode) {
                        eventPrenotation(qrCode);

                    }

                    @Override
                    public void qrCodeNotFound() {
                    }
                }));

                cameraProvider.unbindAll();

                cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview);
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    public void eventPrenotation(String result) {

        SharedPreferences sharedPreferences = getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.103:8080/api/postprenotations";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("eventId", result.toString());
            jsonObject.put("userId", id);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getString("result").equals("true")) {
                            Toast.makeText(EventPrenotationActivity.this, "Posto prenotato", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(EventPrenotationActivity.this, "Evento pieno", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(EventPrenotationActivity.this, "Prenotazione già effettuata", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);
            }catch (Exception e) {
            e.printStackTrace();
        }

    }
}