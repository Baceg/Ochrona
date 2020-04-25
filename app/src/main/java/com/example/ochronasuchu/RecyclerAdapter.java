package com.example.ochronasuchu;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<ItemDto> mRecList;
    Context mContext;
    Dialog mDialog;



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        public ImageView mImageView;
        public TextView mTextView4;
        public TextView mTextView5;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView4 = itemView.findViewById(R.id.textView4);
            mTextView5 = itemView.findViewById(R.id.textView5);

        }
    }


    public RecyclerAdapter(Context myContext,ArrayList<ItemDto> recList){

        mContext = myContext;
        mRecList = recList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        final RecyclerViewHolder rvh = new RecyclerViewHolder(V);

        //Dialog ini
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.item_dialog);
        //onclick na elemencie
        rvh.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"TEST CLICK "+String.valueOf(rvh.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                ItemDto clickedItem = mRecList.get(rvh.getAdapterPosition());
                writeDialog(clickedItem);
                mDialog.show();
            }
        });

        return rvh;
    }

    private void writeDialog(ItemDto clickedItem){
        //no musi być po prostu tutaj deklaracja
        TextView dialog_text_1 = (TextView) mDialog.findViewById(R.id.textView7);
        TextView dialog_text_2 = (TextView) mDialog.findViewById(R.id.textView8);
        ImageView dialog_image_1 = mDialog.findViewById(R.id.imageView2);


        //set texty
        dialog_text_1.setText(clickedItem.getProd());
        dialog_text_2.setText(clickedItem.getModel());
        if (clickedItem.getTyp().equals("N")) {
            dialog_image_1.setImageResource(R.drawable.ic_headset_white_24dp);
        } else {
            dialog_image_1.setImageResource(R.drawable.ic_hearing_black_24dp);
        }


    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ItemDto currentItem = mRecList.get(position);
        holder.mTextView4.setText(currentItem.getProd());
        holder.mTextView5.setText(currentItem.getModel());
        if (currentItem.getTyp().equals("N")){
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
