package com.example.ochronasuchu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private  ArrayList<ItemDto> mRecList;


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        //public ImageView mImageView;
        public TextView mTextView4;
        public TextView mTextView5;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView4 = itemView.findViewById(R.id.textView4);
            mTextView5 = itemView.findViewById(R.id.textView5);
        }
    }

    public RecyclerAdapter(ArrayList<ItemDto> recList){
        mRecList = recList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(V);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ItemDto currentItem = mRecList.get(position);
        holder.mTextView4.setText(currentItem.getProd());
        holder.mTextView5.setText(currentItem.getModel());
    }

    @Override
    public int getItemCount() {
        return mRecList.size();
    }
}
