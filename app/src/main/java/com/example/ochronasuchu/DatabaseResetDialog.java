package com.example.ochronasuchu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DatabaseResetDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String currentDate = ((MainActivity) Objects.requireNonNull(getActivity())).getCurrentDatabaseVersion();
        String stockDate = ((MainActivity) Objects.requireNonNull(getActivity())).getStockDatabaseVersion();



        builder.setTitle("Przywrócić fabryczną bazę danych?")
                .setMessage("Obecna baza stworzona: "+currentDate+System.lineSeparator()+"Domyślna: "+stockDate)
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity) Objects.requireNonNull(getActivity())).installDefaultDB();
                        ((MainActivity)getActivity()).writeRecords();
                        ((MainActivity)getActivity()).refreshRecycler();
                        Toast.makeText(getContext(),"Zainstalowano domyślną Bazę Danych",Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();

    }
}
