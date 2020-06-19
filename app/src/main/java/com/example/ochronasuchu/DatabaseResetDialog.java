package com.example.ochronasuchu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DatabaseResetDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String currentDate = ((MainActivity) Objects.requireNonNull(getActivity())).getCurrentDatabaseTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String stockDate = ((MainActivity)getActivity()).getStockDatabaseTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));



        builder.setTitle("Przywrócić domyślną bazę danych?")
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
                        ((MainActivity)getActivity()).setCurrentDatabaseTime(((MainActivity)getActivity()).getStockDatabaseTime());
                        Toast.makeText(getContext(),"Zainstalowano domyślną Bazę Danych",Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();

    }
}
