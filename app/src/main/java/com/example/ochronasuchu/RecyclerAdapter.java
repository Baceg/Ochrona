package com.example.ochronasuchu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
//Obsługuje widget Recyclerview
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<ItemHearingProtector> mRecList;
    private Context mContext;
    private Dialog mDialog;

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private ImageView mImageView;
        private TextView mTextView4;
        private TextView mTextView5;
        private TextView mTextView6;

        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView4 = itemView.findViewById(R.id.textView4);
            mTextView5 = itemView.findViewById(R.id.textView5);
            mTextView6 = itemView.findViewById(R.id.textView6);

        }
    }

    RecyclerAdapter(Context myContext, ArrayList<ItemHearingProtector> recList){
        mContext = myContext;
        mRecList = recList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        final RecyclerViewHolder rvh = new RecyclerViewHolder(V);
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_item);
        rvh.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemHearingProtector clickedItem = mRecList.get(rvh.getAdapterPosition());
                writeDialog(clickedItem);
                mDialog.show();
            }
        });
        return rvh;
    }

    @SuppressLint("SetTextI18n")
    private void writeDialog(final ItemHearingProtector clickedItem){
        final RelativeLayout dialog_graph_layout = mDialog.findViewById(R.id.graph_layout);
        final TableLayout dialog_table_layout = mDialog.findViewById(R.id.table_layout);
        TextView dialog_producer = mDialog.findViewById(R.id.dialog_item_producer);
        TextView dialog_model = mDialog.findViewById(R.id.dialog_item_model);
        ImageView dialog_image = mDialog.findViewById(R.id.dialog_item_image);
        GraphView dialog_graph = mDialog.findViewById(R.id.graph);
        TextView dialog_L = mDialog.findViewById(R.id.dialog_item_L);
        TextView dialog_M = mDialog.findViewById(R.id.dialog_item_M);
        TextView dialog_H = mDialog.findViewById(R.id.dialog_item_H);
        TextView dialog_SNR = mDialog.findViewById(R.id.dialog_item_SNR);
        Button dialog_button_delete = mDialog.findViewById(R.id.dialog_item_delete_button);
        TextView dialog_mf125 = mDialog.findViewById(R.id.dialog_mf125);
        TextView dialog_mf250 = mDialog.findViewById(R.id.dialog_mf250);
        TextView dialog_mf500 = mDialog.findViewById(R.id.dialog_mf500);
        TextView dialog_mf1000 = mDialog.findViewById(R.id.dialog_mf1000);
        TextView dialog_mf2000 = mDialog.findViewById(R.id.dialog_mf2000);
        TextView dialog_mf4000 = mDialog.findViewById(R.id.dialog_mf4000);
        TextView dialog_mf8000 = mDialog.findViewById(R.id.dialog_mf8000);
        TextView dialog_sf125 = mDialog.findViewById(R.id.dialog_sf125);
        TextView dialog_sf250 = mDialog.findViewById(R.id.dialog_sf250);
        TextView dialog_sf500 = mDialog.findViewById(R.id.dialog_sf500);
        TextView dialog_sf1000 = mDialog.findViewById(R.id.dialog_sf1000);
        TextView dialog_sf2000 = mDialog.findViewById(R.id.dialog_sf2000);
        TextView dialog_sf4000 = mDialog.findViewById(R.id.dialog_sf4000);
        TextView dialog_sf8000 = mDialog.findViewById(R.id.dialog_sf8000);
        TextView dialog_apv125 = mDialog.findViewById(R.id.dialog_apv125);
        TextView dialog_apv250 = mDialog.findViewById(R.id.dialog_apv250);
        TextView dialog_apv500 = mDialog.findViewById(R.id.dialog_apv500);
        TextView dialog_apv1000 = mDialog.findViewById(R.id.dialog_apv1000);
        TextView dialog_apv2000 = mDialog.findViewById(R.id.dialog_apv2000);
        TextView dialog_apv4000 = mDialog.findViewById(R.id.dialog_apv4000);
        TextView dialog_apv8000 = mDialog.findViewById(R.id.dialog_apv8000);
        final TextView dialog_text_graph = mDialog.findViewById(R.id.dialog_text_graph);
        final TextView dialog_text_table = mDialog.findViewById(R.id.dialog_text_table);

        //set obrazek i nazwa
        if (clickedItem.getTyp().equals("N")) {
            dialog_image.setImageResource(R.drawable.ic_headset_white_24dp);
        } else {
            dialog_image.setImageResource(R.drawable.ic_hearing_white_24dp);
        }
        dialog_producer.setText(clickedItem.getProd());
        dialog_model.setText(clickedItem.getModel());
        //graph
        dialog_graph.removeAllSeries();
        dialog_graph.setTitle("Charakterystyka Tłumienia");
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(125, Float.parseFloat(clickedItem.getAPV125())),
                new DataPoint(250, Float.parseFloat(clickedItem.getAPV250())),
                new DataPoint(500, Float.parseFloat(clickedItem.getAPV500())),
                new DataPoint(1000, Float.parseFloat(clickedItem.getAPV1000())),
                new DataPoint(2000, Float.parseFloat(clickedItem.getAPV2000())),
                new DataPoint(4000, Float.parseFloat(clickedItem.getAPV4000())),
                new DataPoint(8000, Float.parseFloat(clickedItem.getAPV8000()))
        });
        series.setColor(Color.RED);
        series.setTitle("APV");
        dialog_graph.addSeries(series);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(125, Float.parseFloat(clickedItem.getMf125())),
                new DataPoint(250, Float.parseFloat(clickedItem.getMf250())),
                new DataPoint(500, Float.parseFloat(clickedItem.getMf500())),
                new DataPoint(1000, Float.parseFloat(clickedItem.getMf1000())),
                new DataPoint(2000, Float.parseFloat(clickedItem.getMf2000())),
                new DataPoint(4000, Float.parseFloat(clickedItem.getMf4000())),
                new DataPoint(8000, Float.parseFloat(clickedItem.getMf8000()))
        });
        series2.setColor(Color.BLUE);
        series2.setTitle("Mf");
        dialog_graph.addSeries(series2);
        dialog_graph.getLegendRenderer().setVisible(true);

        dialog_mf125.setText(clickedItem.getMf125());
        dialog_mf250.setText(clickedItem.getMf250());
        dialog_mf500.setText(clickedItem.getMf500());
        dialog_mf1000.setText(clickedItem.getMf1000());
        dialog_mf2000.setText(clickedItem.getMf2000());
        dialog_mf4000.setText(clickedItem.getMf4000());
        dialog_mf8000.setText(clickedItem.getMf8000());
        dialog_sf125.setText(clickedItem.getSf125());
        dialog_sf250.setText(clickedItem.getSf250());
        dialog_sf500.setText(clickedItem.getSf500());
        dialog_sf1000.setText(clickedItem.getSf1000());
        dialog_sf2000.setText(clickedItem.getSf2000());
        dialog_sf4000.setText(clickedItem.getSf4000());
        dialog_sf8000.setText(clickedItem.getSf8000());
        dialog_apv125.setText(clickedItem.getAPV125());
        dialog_apv250.setText(clickedItem.getAPV250());
        dialog_apv500.setText(clickedItem.getAPV500());
        dialog_apv1000.setText(clickedItem.getAPV1000());
        dialog_apv2000.setText(clickedItem.getAPV2000());
        dialog_apv4000.setText(clickedItem.getAPV4000());
        dialog_apv8000.setText(clickedItem.getAPV8000());

        dialog_L.setText("L: "+clickedItem.getL());
        dialog_M.setText("M: "+clickedItem.getM());
        dialog_H.setText("H: "+clickedItem.getH());
        dialog_SNR.setText("SNR: "+clickedItem.getSNR());


        dialog_text_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_graph_layout.setVisibility(View.VISIBLE);
                dialog_table_layout.setVisibility(View.GONE);
                dialog_text_graph.setBackgroundColor(Color.parseColor("#FFFFFF"));
                dialog_text_table.setBackgroundColor(Color.parseColor("#7A7A7A"));

            }
        });

        dialog_text_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_graph_layout.setVisibility(View.GONE);
                dialog_table_layout.setVisibility(View.VISIBLE);
                dialog_text_table.setBackgroundColor(Color.parseColor("#FFFFFF"));
                dialog_text_graph.setBackgroundColor(Color.parseColor("#7A7A7A"));

            }
        });

        dialog_table_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog_table_layout.setVisibility(View.GONE);
                dialog_graph_layout.setVisibility(View.VISIBLE);
                dialog_text_graph.setBackgroundColor(Color.parseColor("#FFFFFF"));
                dialog_text_table.setBackgroundColor(Color.parseColor("#7A7A7A"));

            }
        });

        dialog_graph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog_graph_layout.setVisibility(View.GONE);
                dialog_table_layout.setVisibility(View.VISIBLE);
                dialog_text_graph.setBackgroundColor(Color.parseColor("#7A7A7A"));
                dialog_text_table.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });

        dialog_graph_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog_graph_layout.setVisibility(View.GONE);
                dialog_table_layout.setVisibility(View.VISIBLE);
                dialog_text_graph.setBackgroundColor(Color.parseColor("#7A7A7A"));
                dialog_text_table.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }
        });

        //Usuń kliknięty
        dialog_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                list.add(clickedItem.getTyp());
                list.add(clickedItem.getProd());
                list.add(clickedItem.getModel());
                list.add(clickedItem.getMf125());
                list.add(clickedItem.getMf250());
                list.add(clickedItem.getMf500());
                list.add(clickedItem.getMf1000());
                list.add(clickedItem.getMf2000());
                list.add(clickedItem.getMf4000());
                list.add(clickedItem.getMf8000());
                list.add(clickedItem.getAPV125());
                list.add(clickedItem.getAPV250());
                list.add(clickedItem.getAPV500());
                list.add(clickedItem.getAPV1000());
                list.add(clickedItem.getAPV2000());
                list.add(clickedItem.getAPV4000());
                list.add(clickedItem.getAPV8000());
                list.add(clickedItem.getH());
                list.add(clickedItem.getM());
                list.add(clickedItem.getL());
                list.add(clickedItem.getSNR());
                list.add(clickedItem.getCerty());
                ((MainActivity)mContext).deleteFromDatabase(list);
                ((MainActivity)mContext).writeRecordsUser();
                ((MainActivity)mContext).writeRecords();
                ((MainActivity)mContext).refreshRecycler();
                mDialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ItemHearingProtector currentItem = mRecList.get(position);
        holder.mTextView4.setText(currentItem.getProd());
        holder.mTextView5.setText(currentItem.getModel());
        holder.mTextView6.setText("L: "+currentItem.getL()+" M: "+currentItem.getM()+" H: "+currentItem.getH());
        if (currentItem.getTyp().equals("N")){
            if (currentItem.getCerty().equals("user"))
                holder.mImageView.setImageResource(R.drawable.ic_headset_gold_24dp);
            else
                holder.mImageView.setImageResource(R.drawable.ic_headset_black_24dp);
        } else
            if (currentItem.getCerty().equals("user"))
                holder.mImageView.setImageResource(R.drawable.ic_hearing_gold_24dp);
            else
            holder.mImageView.setImageResource(R.drawable.ic_hearing_black_24dp);


    }
    @Override
    public int getItemCount() {
        return mRecList.size();
    }
}
