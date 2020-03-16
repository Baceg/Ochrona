package com.example.ochronasuchu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.net.InetAddress;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import android.widget.Toast;


public class InfoFragment extends Fragment {

    Button buttonUpdate;
    DatabaseHelper myDB;
    Boolean dataInserted = false;
    Integer deletedRows = 0;
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
            if (isInternetAvailable(urlString)==false){
            //    return null;
            }
            //else {
                Document doc = Jsoup.connect(urlString).get();
                Element body = doc.body();
                builder.append(body.text());
                String seperate_records[] = String.valueOf(builder).split(" @@@ ");
                placeAddress = Arrays.asList(seperate_records);
                return placeAddress; // return whatever you need
            //}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isInternetAvailable(String urlString) {
        try {
            InetAddress ipAddr = InetAddress.getByName(urlString);
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }




    public void UpdateData(){
        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final List<String> addressList = getTextFromWeb("http://10.0.2.2/ochslu/");
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        dataInserted = false;
                                        if (addressList != null) {
                                            deletedRows = myDB.deleteWebData();
                                            for (int i = 0; i < addressList.size() / 29; i++)
                                                myDB.insertData(addressList.get(i * 29), addressList.get(i * 29 + 1), addressList.get(i * 29 + 2), addressList.get(i * 29 + 3), addressList.get(i * 29 + 4), addressList.get(i * 29 + 5),
                                                        addressList.get(i * 29 + 6), addressList.get(i * 29 + 7), addressList.get(i * 29 + 8), addressList.get(i * 29 + 9), addressList.get(i * 29 + 10), addressList.get(i * 29 + 11),
                                                        addressList.get(i * 29 + 12), addressList.get(i * 29 + 13), addressList.get(i * 29 + 14), addressList.get(i * 29 + 15), addressList.get(i * 29 + 16), addressList.get(i * 29 + 17),
                                                        addressList.get(i * 29 + 18), addressList.get(i * 29 + 19), addressList.get(i * 29 + 20), addressList.get(i * 29 + 21), addressList.get(i * 29 + 22), addressList.get(i * 29 + 23),
                                                        addressList.get(i * 29 + 24), addressList.get(i * 29 + 25), addressList.get(i * 29 + 26), addressList.get(i * 29 + 27), addressList.get(i * 29 + 28));

                                            dataInserted = true;
                                        }

                                        if (deletedRows > 0 && dataInserted == true) {
                                            Toast.makeText(getContext(), "Aktualizacja ukończona pomyślnie", Toast.LENGTH_LONG).show();
                                        }
                                        else if (deletedRows > 0 && dataInserted == false) {
                                            Toast.makeText(getContext(), "Usunieto i nie dano", Toast.LENGTH_LONG).show();
                                        }
                                        else if (deletedRows == 0 && dataInserted == true) {
                                            Toast.makeText(getContext(), "Pomyślnie dodano", Toast.LENGTH_LONG).show();
                                        }
                                        else if (deletedRows == 0 && dataInserted == false){
                                            Toast.makeText(getContext(), "Nie usunieto i nie dano", Toast.LENGTH_LONG).show();
                                        }




                                    }
                                });
                            }
                        }).start();



                    }
                }
        );




    }



}
