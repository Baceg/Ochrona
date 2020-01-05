package com.example.ochronasuchu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



public class InfoFragment extends Fragment {

    Button buttonUpdate;
    DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_info,container,false);
        myDB = new DatabaseHelper(this.getActivity());
        buttonUpdate = (Button) view.findViewById(R.id.updateButton);


        UpdateData();
    return view;
    }

    public List<String> getTextFromWeb(String urlString)
    {
        URLConnection feedUrl;
        List<String> placeAddress = new ArrayList<>();
        final StringBuilder builder = new StringBuilder();
        try
        {
            //feedUrl = new URL(urlString).openConnection();
            Document doc = Jsoup.connect(urlString).get();
            Element body = doc.body();
            builder.append(body.text());

            String seperate_records[] = String.valueOf(builder).split(" @@@ ");
            placeAddress = Arrays.asList(seperate_records);
            //Log.d("TAG", String.valueOf(builder));
            //InputStream is = feedUrl.getInputStream();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //String line = null;

            //while ((line = reader.readLine()) != null) // read line by line
            //{
            //    placeAddress.add(line); // add line to list
            //}
            //is.close(); // close input stream

            return placeAddress; // return whatever you need
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }






    public void UpdateData(){
        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Integer deletedRows = myDB.deleteWebData();
                        //Boolean dataInserted = false;
                        //nie wiem
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final List<String> addressList = getTextFromWeb("http://10.0.2.2/ochslu/");
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        if (addressList != null) {
                                            for (int i = 0; i < addressList.size() / 29; i++)
                                                myDB.insertData(addressList.get(i * 29), addressList.get(i * 29 + 1), addressList.get(i * 29 + 2), addressList.get(i * 29 + 3), addressList.get(i * 29 + 4), addressList.get(i * 29 + 5),
                                                        addressList.get(i * 29 + 6), addressList.get(i * 29 + 7), addressList.get(i * 29 + 8), addressList.get(i * 29 + 9), addressList.get(i * 29 + 10), addressList.get(i * 29 + 11),
                                                        addressList.get(i * 29 + 12), addressList.get(i * 29 + 13), addressList.get(i * 29 + 14), addressList.get(i * 29 + 15), addressList.get(i * 29 + 16), addressList.get(i * 29 + 17),
                                                        addressList.get(i * 29 + 18), addressList.get(i * 29 + 19), addressList.get(i * 29 + 20), addressList.get(i * 29 + 21), addressList.get(i * 29 + 22), addressList.get(i * 29 + 23),
                                                        addressList.get(i * 29 + 24), addressList.get(i * 29 + 25), addressList.get(i * 29 + 26), addressList.get(i * 29 + 27), addressList.get(i * 29 + 28));

                                            //update ui
                                        }
                                    }
                                });
                            }
                        }).start();
                        /*
                        if (deletedRows > 0 && dataInserted == true) {
                            Toast.makeText(MainActivity.this, "Aktualizacja ukończona pomyślnie", Toast.LENGTH_LONG).show();
                            showRecords();
                        }
                        if (deletedRows > 0 && dataInserted == false) {
                            Toast.makeText(MainActivity.this, "Usunieto i nie dano", Toast.LENGTH_LONG).show();
                            showRecords();
                        }
                        if (deletedRows < 0 && dataInserted == true) {
                            Toast.makeText(MainActivity.this, "Pomyślnie dodano", Toast.LENGTH_LONG).show();
                            showRecords();
                        } else {
                            Toast.makeText(MainActivity.this, "Nie usunieto i nie dano", Toast.LENGTH_LONG).show();
                            showRecords();
                        }
                        */
                    }
                }
        );




    }



}
