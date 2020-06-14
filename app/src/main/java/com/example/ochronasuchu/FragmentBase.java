package com.example.ochronasuchu;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
//fragment baza
public class FragmentBase extends Fragment implements UpdateInterface {

    private RecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ArrayList<ItemHearingProtector> mlist = null;
        if (getArguments() != null) {
            mlist = (ArrayList<ItemHearingProtector>) getArguments().getSerializable("bundle_key");
        }
        RecyclerView mRecyclerView1 = view.findViewById(R.id.recycler_view_baza);
        mRecyclerView1.setHasFixedSize(true);
        mAdapter = new RecyclerAdapter(getContext(),mlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView1.setLayoutManager(mLayoutManager);
        mRecyclerView1.setAdapter(mAdapter);
        return view;
    }
    //Umożliwia odświerzanie Recyclera z MainActivity
    public void updateRecycler(){
        mAdapter.notifyDataSetChanged();
    }
}