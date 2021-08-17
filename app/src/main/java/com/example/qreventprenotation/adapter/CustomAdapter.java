package com.example.qreventprenotation.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qreventprenotation.R;
import com.example.qreventprenotation.controller.AdapterActivity;
import com.example.qreventprenotation.model.Prenotation;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Prenotation> list;
    private Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView txtNomeEvento;
        public TextView txtPosto;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_prenotazione);
            txtNomeEvento = view.findViewById(R.id.txtcard_nomeevento);
            txtPosto = view.findViewById(R.id.txtcard_posto);
        }


    }

    public CustomAdapter(List<Prenotation> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_prenotation, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder holderCasted = holder;
        holderCasted.txtNomeEvento.setText(list.get(position).getEventid().getNome());
        holderCasted.txtPosto.setText(list.get(position).getPrenotation().toString());

        holderCasted.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPopup = new Intent(activity.getApplicationContext(), AdapterActivity.class);
                activity.startActivity(openPopup);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
