package com.example.ochronasuchu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
//DialogAddItem obsługuje formularz z dodawnaiem ochronnika do bazy
public class DialogAddItem extends AppCompatDialogFragment {

    private EditText editTextMf125;
    private EditText editTextMf250;
    private EditText editTextMf500;
    private EditText editTextMf1000;
    private EditText editTextMf2000;
    private EditText editTextMf4000;
    private EditText editTextMf8000;
    private EditText editTextApv125;
    private EditText editTextApv250;
    private EditText editTextApv500;
    private EditText editTextApv1000;
    private EditText editTextApv2000;
    private EditText editTextApv4000;
    private EditText editTextApv8000;
    private EditText editTextL;
    private EditText editTextM;
    private EditText editTextH;
    private EditText editTextSNR;
    private EditText editTextProd;
    private EditText editTextModel;
    private ArrayList<String> data = new ArrayList<>();


    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final ViewGroup nullParent = null; //by uniknąć warninga
        View dialog = inflater.inflate(R.layout.dialog_add,nullParent);

        builder.setView(dialog)
                .setTitle("Dodaj")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Zamiana niewypełnionych miejsc w formularzu na 0 (wykeres nie poradziłby sobie z wartościami null, i baza danych ich nie lubi)
                        if (editTextProd.getText().toString().equals("")) editTextProd.setText("-");
                        if (editTextModel.getText().toString().equals("")) editTextModel.setText("-");
                        if (editTextMf125.getText().toString().equals("")) editTextMf125.setText("0");
                        if (editTextMf250.getText().toString().equals("")) editTextMf250.setText("0");
                        if (editTextMf500.getText().toString().equals("")) editTextMf500.setText("0");
                        if (editTextMf1000.getText().toString().equals("")) editTextMf1000.setText("0");
                        if (editTextMf2000.getText().toString().equals("")) editTextMf2000.setText("0");
                        if (editTextMf4000.getText().toString().equals("")) editTextMf4000.setText("0");
                        if (editTextMf8000.getText().toString().equals("")) editTextMf8000.setText("0");
                        if (editTextApv125.getText().toString().equals("")) editTextApv125.setText("0");
                        if (editTextApv250.getText().toString().equals("")) editTextApv250.setText("0");
                        if (editTextApv500.getText().toString().equals("")) editTextApv500.setText("0");
                        if (editTextApv1000.getText().toString().equals("")) editTextApv1000.setText("0");
                        if (editTextApv2000.getText().toString().equals("")) editTextApv2000.setText("0");
                        if (editTextApv4000.getText().toString().equals("")) editTextApv4000.setText("0");
                        if (editTextApv8000.getText().toString().equals("")) editTextApv8000.setText("0");
                        if (editTextH.getText().toString().equals("") || editTextH.getText().toString().equals("00")) editTextH.setText("0");
                        if (editTextM.getText().toString().equals("") || editTextM.getText().toString().equals("00")) editTextM.setText("0");
                        if (editTextL.getText().toString().equals("") || editTextL.getText().toString().equals("00")) editTextL.setText("0");
                        if (editTextSNR.getText().toString().equals("") || editTextSNR.getText().toString().equals("00")) editTextSNR.setText("0");
                        //wprowadenie wartości do listy która będzie przekazana do bazy danych
                        data.add(editTextProd.getText().toString());
                        data.add(editTextModel.getText().toString());
                        data.add(editTextMf125.getText().toString());
                        data.add(editTextMf250.getText().toString());
                        data.add(editTextMf500.getText().toString());
                        data.add(editTextMf1000.getText().toString());
                        data.add(editTextMf2000.getText().toString());
                        data.add(editTextMf4000.getText().toString());
                        data.add(editTextMf8000.getText().toString());
                        data.add(String.valueOf(Math.round((Float.valueOf(editTextMf125.getText().toString())-Float.valueOf(editTextApv125.getText().toString()))*10.0)/10.0));
                        data.add(String.valueOf(Math.round((Float.valueOf(editTextMf250.getText().toString())-Float.valueOf(editTextApv250.getText().toString()))*10.0)/10.0));
                        data.add(String.valueOf(Math.round((Float.valueOf(editTextMf500.getText().toString())-Float.valueOf(editTextApv500.getText().toString()))*10.0)/10.0));
                        data.add(String.valueOf(Math.round((Float.valueOf(editTextMf1000.getText().toString())-Float.valueOf(editTextApv1000.getText().toString()))*10.0)/10.0));
                        data.add(String.valueOf(Math.round((Float.valueOf(editTextMf2000.getText().toString())-Float.valueOf(editTextApv2000.getText().toString()))*10.0)/10.0));
                        data.add(String.valueOf(Math.round((Float.valueOf(editTextMf4000.getText().toString())-Float.valueOf(editTextApv4000.getText().toString()))*10.0)/10.0));
                        data.add(String.valueOf(Math.round((Float.valueOf(editTextMf8000.getText().toString())-Float.valueOf(editTextApv8000.getText().toString()))*10.0)/10.0));
                        data.add(editTextApv125.getText().toString());
                        data.add(editTextApv250.getText().toString());
                        data.add(editTextApv500.getText().toString());
                        data.add(editTextApv1000.getText().toString());
                        data.add(editTextApv2000.getText().toString());
                        data.add(editTextApv4000.getText().toString());
                        data.add(editTextApv8000.getText().toString());
                        data.add(editTextH.getText().toString());
                        data.add(editTextM.getText().toString());
                        data.add(editTextL.getText().toString());
                        data.add(editTextSNR.getText().toString());
                        //wpisanie do bazy danych
                        ((MainActivity) Objects.requireNonNull(getActivity())).addToDatabase(data);
                        //odczytanie z bazy danych
                        ((MainActivity) getActivity()).writeRecords();
                        ((MainActivity) getActivity()).writeRecordsUser();
                        //odświerzenie tego co się wyświetla
                        ((MainActivity) getActivity()).refreshRecycler();
                    }
                });
        //podłączenie layoutu do klasy, ustalenie filtrów na formularzach
        editTextProd = dialog.findViewById(R.id.edit_prod);
        editTextModel = dialog.findViewById(R.id.edit_model);
        editTextMf125 = dialog.findViewById(R.id.edit_mf125);
        editTextMf125.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf250 = dialog.findViewById(R.id.edit_mf250);
        editTextMf250.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf500 = dialog.findViewById(R.id.edit_mf500);
        editTextMf500.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf1000 = dialog.findViewById(R.id.edit_mf1000);
        editTextMf1000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf2000 = dialog.findViewById(R.id.edit_mf2000);
        editTextMf2000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf4000 = dialog.findViewById(R.id.edit_mf4000);
        editTextMf4000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf8000 = dialog.findViewById(R.id.edit_mf8000);
        editTextMf8000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextApv125 = dialog.findViewById(R.id.edit_apv125);
        editTextApv125.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextApv250 = dialog.findViewById(R.id.edit_apv250);
        editTextApv250.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextApv500 = dialog.findViewById(R.id.edit_apv500);
        editTextApv500.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextApv1000 = dialog.findViewById(R.id.edit_apv1000);
        editTextApv1000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextApv2000 = dialog.findViewById(R.id.edit_apv2000);
        editTextApv2000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextApv4000 = dialog.findViewById(R.id.edit_apv4000);
        editTextApv4000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextApv8000 = dialog.findViewById(R.id.edit_apv8000);
        editTextApv8000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextH = dialog.findViewById(R.id.edit_H);
        editTextM = dialog.findViewById(R.id.edit_M);
        editTextL = dialog.findViewById(R.id.edit_L);
        editTextSNR = dialog.findViewById(R.id.edit_SNR);

        return builder.create();
    }

}
