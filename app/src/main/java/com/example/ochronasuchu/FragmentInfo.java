package com.example.ochronasuchu;

import android.annotation.SuppressLint;
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

import java.util.Objects;

//Fragment info
public class FragmentInfo extends Fragment {

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,container,false);
        Button buttonUpdate = view.findViewById(R.id.updateButton);
        Button buttonReset = view.findViewById(R.id.resetButton);
        TextView infoText = view.findViewById(R.id.textViewInfo);
        TextView versionText = view.findViewById(R.id.textView0);
        versionText.setText("Obecna Wersja: "+((MainActivity) Objects.requireNonNull(getActivity())).getCurrentDatabaseVersion());

        infoText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //miejsce na możliwe funkcje dla administratora
                ((MainActivity) Objects.requireNonNull(getActivity())).clearDatabase();
                ((MainActivity)getActivity()).writeRecords();
                ((MainActivity)getActivity()).refreshRecycler();
                Toast.makeText(getContext(),"Usunięto bazę",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        );

        buttonReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) Objects.requireNonNull(getActivity())).openDialogResetDatabase();

                //((MainActivity) Objects.requireNonNull(getActivity())).installDefaultDB();
                //((MainActivity)getActivity()).writeRecords();
                //((MainActivity)getActivity()).refreshRecycler();
                //Toast.makeText(getContext(),"Zainstalowano domyślną",Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        buttonUpdate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) Objects.requireNonNull(getActivity())).updateData();
                //((MainActivity)getActivity()).refreshRecycler();

                return true;
            }
        });
    return view;
    }

}
