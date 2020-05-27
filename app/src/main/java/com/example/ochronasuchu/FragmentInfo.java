package com.example.ochronasuchu;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Fragment info
public class FragmentInfo extends Fragment {

    Button buttonUpdate;
    TextView infoText;
    DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,container,false);
        myDB = new DatabaseHelper(this.getActivity());
        buttonUpdate = (Button) view.findViewById(R.id.updateButton);
        infoText = view.findViewById(R.id.textViewInfo);

        infoText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //miejsce na możliwe funkcje dla administratora
                ((MainActivity)getActivity()).clearDatabase();
                Toast.makeText(getContext(),"easter egg",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        );

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).updateData();
                ((MainActivity)getActivity()).writeRecords();
                ((MainActivity)getActivity()).refreshRecycler();
            }
        });
    return view;
    }
}
