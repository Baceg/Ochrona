package com.example.ochronasuchu;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BazaFragment extends Fragment {

    private RecyclerView mRecyclerView1;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_baza, container, false);

        showRecords();
        mRecyclerView1 = view.findViewById(R.id.recycler_view_baza);
        mRecyclerView1.setHasFixedSize(true);
        myDB = new DatabaseHelper(this.getActivity());
        mLayoutManager = new LinearLayoutManager(this.getActivity());

        mRecyclerView1.setLayoutManager(mLayoutManager);
        mRecyclerView1.setAdapter(mAdapter);



        return view;
    }


    public void showRecords() {
        myDB = new DatabaseHelper(this.getActivity());
        ArrayList<ItemDto> listaOchron = new ArrayList<>();
        Cursor cursor = myDB.getAllData();
        try {
            while (cursor.moveToNext()) {
                listaOchron.add(new ItemDto(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                        cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17),
                        cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23),
                        cursor.getString(24), cursor.getString(25), cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29)));
            }

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        myDB.close();
        //setContentView(R.layout.fragment_baza);

        mAdapter = new RecyclerAdapter(listaOchron);
        //setContentView(R.layout.activity_main);
    }

}