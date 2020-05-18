package com.example.ochronasuchu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddItemDialog extends AppCompatDialogFragment {

    private EditText editTextMf125;
    private EditText editTextMf250;
    private EditText editTextMf500;
    private EditText editTextMf1000;
    private EditText editTextMf2000;
    private EditText editTextMf4000;
    private EditText editTextMf8000;
    private EditText editTextAvp125;
    private EditText editTextAvp250;
    private EditText editTextAvp500;
    private EditText editTextAvp1000;
    private EditText editTextAvp2000;
    private EditText editTextAvp4000;
    private EditText editTextAvp8000;
    private EditText editTextL;
    private EditText editTextM;
    private EditText editTextH;
    private EditText editTextSNR;
    private EditText editTextProd;
    private EditText editTextModel;
    private RadioGroup radioGroup;
  //  private RadioButton radioWkladka;
 //   private RadioButton radioNausznik;
    public ArrayList<String> data = new ArrayList<String>();






    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_add,null);



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

                        if (editTextProd.getText().toString().equals("")) editTextProd.setText("-");
                        if (editTextModel.getText().toString().equals("")) editTextModel.setText("-");
                        if (editTextMf125.getText().toString().equals("")) editTextMf125.setText("0");
                        if (editTextMf250.getText().toString().equals("")) editTextMf250.setText("0");
                        if (editTextMf500.getText().toString().equals("")) editTextMf500.setText("0");
                        if (editTextMf1000.getText().toString().equals("")) editTextMf1000.setText("0");
                        if (editTextMf2000.getText().toString().equals("")) editTextMf2000.setText("0");
                        if (editTextMf4000.getText().toString().equals("")) editTextMf4000.setText("0");
                        if (editTextMf8000.getText().toString().equals("")) editTextMf8000.setText("0");
                        if (editTextAvp125.getText().toString().equals("")) editTextAvp125.setText("0");
                        if (editTextAvp250.getText().toString().equals("")) editTextAvp250.setText("0");
                        if (editTextAvp500.getText().toString().equals("")) editTextAvp500.setText("0");
                        if (editTextAvp1000.getText().toString().equals("")) editTextAvp1000.setText("0");
                        if (editTextAvp2000.getText().toString().equals("")) editTextAvp2000.setText("0");
                        if (editTextAvp4000.getText().toString().equals("")) editTextAvp4000.setText("0");
                        if (editTextAvp8000.getText().toString().equals("")) editTextAvp8000.setText("0");
                        if (editTextH.getText().toString().equals("")) editTextH.setText("0");
                        if (editTextM.getText().toString().equals("")) editTextM.setText("0");
                        if (editTextL.getText().toString().equals("")) editTextL.setText("0");
                        if (editTextSNR.getText().toString().equals("")) editTextSNR.setText("0");

                        data.add(editTextProd.getText().toString());
                        data.add(editTextModel.getText().toString());
                        data.add(editTextMf125.getText().toString());
                        data.add(editTextMf250.getText().toString());
                        data.add(editTextMf500.getText().toString());
                        data.add(editTextMf1000.getText().toString());
                        data.add(editTextMf2000.getText().toString());
                        data.add(editTextMf4000.getText().toString());
                        data.add(editTextMf8000.getText().toString());
                        data.add(String.valueOf(Float.valueOf(editTextMf125.getText().toString())-Float.valueOf(editTextAvp125.getText().toString())));
                        data.add(String.valueOf(Float.valueOf(editTextMf250.getText().toString())-Float.valueOf(editTextAvp250.getText().toString())));
                        data.add(String.valueOf(Float.valueOf(editTextMf500.getText().toString())-Float.valueOf(editTextAvp500.getText().toString())));
                        data.add(String.valueOf(Float.valueOf(editTextMf1000.getText().toString())-Float.valueOf(editTextAvp1000.getText().toString())));
                        data.add(String.valueOf(Float.valueOf(editTextMf2000.getText().toString())-Float.valueOf(editTextAvp2000.getText().toString())));
                        data.add(String.valueOf(Float.valueOf(editTextMf4000.getText().toString())-Float.valueOf(editTextAvp4000.getText().toString())));
                        data.add(String.valueOf(Float.valueOf(editTextMf8000.getText().toString())-Float.valueOf(editTextAvp8000.getText().toString())));
                        data.add(editTextAvp125.getText().toString());
                        data.add(editTextAvp250.getText().toString());
                        data.add(editTextAvp500.getText().toString());
                        data.add(editTextAvp1000.getText().toString());
                        data.add(editTextAvp2000.getText().toString());
                        data.add(editTextAvp4000.getText().toString());
                        data.add(editTextAvp8000.getText().toString());
                        data.add(editTextH.getText().toString());
                        data.add(editTextM.getText().toString());
                        data.add(editTextL.getText().toString());
                        data.add(editTextSNR.getText().toString());
                        ((MainActivity)getActivity()).addToDatabase(data);
                        ((MainActivity) getActivity()).writeRecords();
                        ((MainActivity) getActivity()).writeRecordsUser();
                        ((MainActivity) getActivity()).refreshRecycler();
                    }
                });

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
        editTextAvp125 = dialog.findViewById(R.id.edit_avp125);
        editTextAvp125.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp250 = dialog.findViewById(R.id.edit_avp250);
        editTextAvp250.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp500 = dialog.findViewById(R.id.edit_avp500);
        editTextAvp500.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp1000 = dialog.findViewById(R.id.edit_avp1000);
        editTextAvp1000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp2000 = dialog.findViewById(R.id.edit_avp2000);
        editTextAvp2000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp4000 = dialog.findViewById(R.id.edit_avp4000);
        editTextAvp4000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp8000 = dialog.findViewById(R.id.edit_avp8000);
        editTextAvp8000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextH = dialog.findViewById(R.id.edit_H);
        editTextM = dialog.findViewById(R.id.edit_M);
        editTextL = dialog.findViewById(R.id.edit_L);
        editTextSNR = dialog.findViewById(R.id.edit_SNR);
        radioGroup = dialog.findViewById(R.id.radio_group);


        return builder.create();
    }



}
