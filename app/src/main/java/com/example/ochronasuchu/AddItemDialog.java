package com.example.ochronasuchu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private RadioButton radioButton;

    /*private ArrayList<String> mf;
    private ArrayList<String> avp;
    private ArrayList<String> dane;
    */





    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add,null);

        builder.setView(view)
                .setTitle("Dodaj")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                    }
                });

        editTextProd = view.findViewById(R.id.edit_prod);
        editTextModel = view.findViewById(R.id.edit_model);
        editTextMf125 = view.findViewById(R.id.edit_mf125);
        editTextMf125.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf250 = view.findViewById(R.id.edit_mf250);
        editTextMf250.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf500 = view.findViewById(R.id.edit_mf500);
        editTextMf500.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf1000 = view.findViewById(R.id.edit_mf1000);
        editTextMf1000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf2000 = view.findViewById(R.id.edit_mf2000);
        editTextMf2000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf4000 = view.findViewById(R.id.edit_mf4000);
        editTextMf4000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextMf8000 = view.findViewById(R.id.edit_mf8000);
        editTextMf8000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp125 = view.findViewById(R.id.edit_avp125);
        editTextAvp125.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp250 = view.findViewById(R.id.edit_avp250);
        editTextAvp250.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp500 = view.findViewById(R.id.edit_avp500);
        editTextAvp500.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp1000 = view.findViewById(R.id.edit_avp1000);
        editTextAvp1000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp2000 = view.findViewById(R.id.edit_avp2000);
        editTextAvp2000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp4000 = view.findViewById(R.id.edit_avp4000);
        editTextAvp4000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextAvp8000 = view.findViewById(R.id.edit_avp8000);
        editTextAvp8000.setFilters(new InputFilter[] {new DecibelInputFilter()});
        editTextH = view.findViewById(R.id.edit_H);
        editTextM = view.findViewById(R.id.edit_M);
        editTextL = view.findViewById(R.id.edit_L);
        editTextSNR = view.findViewById(R.id.edit_SNR);
        radioGroup = view.findViewById(R.id.radio_group);



        return builder.create();
    }

    public void checkButton(View v){
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = getView().findViewById(radioID);
        if (radioButton.getText().equals("Nausznik")){
            //todo
        }
        else{
            //todo
        }
    }

}
