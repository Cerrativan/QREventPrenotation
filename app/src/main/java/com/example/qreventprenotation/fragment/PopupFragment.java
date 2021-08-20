package com.example.qreventprenotation.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qreventprenotation.R;
import com.example.qreventprenotation.model.Prenotation;

import org.json.JSONException;
import org.json.JSONObject;


public class PopupFragment extends DialogFragment {

    Button button;
    TextView txt_nome, txt_id, txt_data, txt_ora, txt_posto;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_layout, null);
        bindData(view);
        txt_nome.setText(getArguments().getString("nome"));
        txt_id.setText(getArguments().getString("id"));
        txt_data.setText(getArguments().getString("data"));
        txt_ora.setText(getArguments().getString("ora"));
        txt_posto.setText(getArguments().getString("posto"));
        builder.setView(view).
                setNegativeButton(R.string.Chiudi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirm = new AlertDialog.Builder(getActivity());
                confirm.setMessage("Vuoi eliminare la prenotazione?");
                confirm.setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                confirm.setPositiveButton(R.string.SI, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletePrenotation();
                    }
                });
                confirm.create();
                confirm.show();
            }
        });

        return builder.create();
    }

    public void bindData(View view) {
        button = view.findViewById(R.id.button_cancella);
        txt_nome = view.findViewById(R.id.popup_nome);
        txt_id = view.findViewById(R.id.popup_id);
        txt_data = view.findViewById(R.id.popup_data);
        txt_ora = view.findViewById(R.id.popup_ora);
        txt_posto = view.findViewById(R.id.popup_posto);
    }

    public void deletePrenotation() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://192.168.1.128:8080/api/deleteprenotation";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("prenotationid", getArguments().getString("id"));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getString("result").equals("true")) {
                            Toast.makeText(getContext(), "Prenotazione eliminata con successo", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Prenotazione non esistente", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Prenotazione non esistente", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}