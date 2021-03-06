package com.example.qreventprenotation.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qreventprenotation.R;
import com.example.qreventprenotation.fragment.PopupFragment;
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
                Bundle args = new Bundle();
                args.putString("id", list.get(position).getPrenotationid().toString());
                args.putString("data", list.get(position).getEventid().getDate());
                args.putString("ora", list.get(position).getEventid().getHour());
                args.putString("posto", list.get(position).getPrenotation().toString());
                args.putString("nome", list.get(position).getEventid().getNome());
                args.putString("luogo", list.get(position).getEventid().getLocation());
                DialogFragment dialogFragment = new PopupFragment();
                dialogFragment.setArguments(args);
                dialogFragment.show(((FragmentActivity)activity).getSupportFragmentManager(), "Popup");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
