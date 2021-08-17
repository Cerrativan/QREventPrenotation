package com.example.qreventprenotation.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.qreventprenotation.R;
import com.example.qreventprenotation.model.Prenotation;


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
                //deletePrenotation();
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

}