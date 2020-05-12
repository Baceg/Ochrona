package com.example.ochronasuchu;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MojeFragment extends Fragment implements UpdateInterface {

    private RecyclerView mRecyclerView1;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //public DatabaseHelper myDB;
    //ArrayList<ItemDto> listaOchron;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_moje,container,false);

        ArrayList<ItemDto> mlist = (ArrayList<ItemDto>) getArguments().getSerializable("bundle_key");

        mRecyclerView1 = view.findViewById(R.id.recycler_view_moje);
        mRecyclerView1.setHasFixedSize(true);
        mAdapter = new RecyclerAdapter(getContext(),mlist);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView1.setLayoutManager(mLayoutManager);
        mRecyclerView1.setAdapter(mAdapter);

        return view;
    }

    public void updateData(){
        mAdapter.notifyDataSetChanged();
    }



}
