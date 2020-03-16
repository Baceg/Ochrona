package com.example.ochronasuchu;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<ItemDto> mRecList;
    private OnItemClickListener mListener;
    //Dialog mDialog;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout item_info;
        public ImageView mImageView;
        public TextView mTextView4;
        public TextView mTextView5;

        public RecyclerViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            item_info =  itemView.findViewById(R.id.fragment_element1);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView4 = itemView.findViewById(R.id.textView4);
            mTextView5 = itemView.findViewById(R.id.textView5);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }


    public RecyclerAdapter(ArrayList<ItemDto> recList){
        mRecList = recList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(V, mListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ItemDto currentItem = mRecList.get(position);
        holder.mTextView4.setText(currentItem.getProd());
        holder.mTextView5.setText(currentItem.getModel());
        if (currentItem.getTyp().toString().equals("N")){
            holder.mImageView.setImageResource(R.drawable.ic_headset_black_24dp);
        } else{
            holder.mImageView.setImageResource(R.drawable.ic_hearing_black_24dp);
        }


    }

    @Override
    public int getItemCount() {
        return mRecList.size();
    }
}
