package com.example.ochronasuchu;

import android.support.annotation.NonNull;
//import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{

    List<ItemDto> list;

    public RecyclerAdapter(List list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //zamienić na nowe
        //holder.prod.setText(list.get(position).getProd());
        //holder.model.setText(list.get(position).getModel());
        //holder.snr.setText(list.get(position).getSNR());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        //CardView cardView;
        //TextView prod;
        //TextView model;
        //TextView snr;

        public Holder(@NonNull View itemView) {
            super(itemView);
            //ni wiem
            //cardView = itemView.findViewById(R.id.item_card);
            //prod = itemView.findViewById(R.id.prod);
            //model = itemView.findViewById(R.id.model);
            //snr = itemView.findViewById(R.id.snr);
        }
    }






}
