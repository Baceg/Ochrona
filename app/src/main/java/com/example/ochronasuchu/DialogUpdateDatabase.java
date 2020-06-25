package com.example.ochronasuchu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogUpdateDatabase extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String currentDate = ((MainActivity) Objects.requireNonNull(getActivity())).getCurrentDatabaseTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String onlineDate = ((MainActivity)getActivity()).getOnlineDatabaseTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pobrać bazę danych z internetu?")
                .setMessage("Obecna baza stworzona: "+currentDate+System.lineSeparator()+"Na serwerze: "+onlineDate)
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity) Objects.requireNonNull(getActivity())).updateData();
                        ((MainActivity)getActivity()).writeRecords();
                        ((MainActivity)getActivity()).setCurrentDatabaseTime(((MainActivity)getActivity()).getOnlineDatabaseTime());
                        ((MainActivity)getActivity()).resetFragmentInfo();
                    }
                });

        return builder.create();
    }
}
