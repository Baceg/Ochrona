package com.example.ochronasuchu;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    DatabaseHelper myDB;
    Button buttonUpdate;


    public void showRecords(){
        myDB = new DatabaseHelper(this);
        ArrayList<ItemDto> listaOchron = new ArrayList<>();
        Cursor cursor = myDB.getAllData();
        /*while (cursor.moveToNext()){
            listaOchron.add(new ItemDto(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                    cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17),
                    cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23),
                    cursor.getString(24), cursor.getString(25), cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29)));
        }
        */
        listaOchron.add(new ItemDto("1","1","1","1","1","1","1","1","1","1",
                "1","1","1","1","1","1","1","1","1","1","1",
                "1","1","1","1","1","1","1","1"));


        setContentView(R.layout.fragment_baza);
        mRecyclerView1 = findViewById(R.id.recycler_view_baza);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAdapter(listaOchron);
        mRecyclerView1.setLayoutManager(mLayoutManager);
        mRecyclerView1.setAdapter(mAdapter);
        setContentView(R.layout.activity_main);


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonUpdate = (Button) findViewById(R.id.updateButton);

        showRecords();
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(naviListener);





    }

    private BottomNavigationView.OnNavigationItemSelectedListener naviListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_baza:
                            selectedFragment = new BazaFragment();
                            break;
                        case R.id.navigation_moje:
                            selectedFragment = new MojeFragment();
                            break;
                        case R.id.navigation_info:
                            selectedFragment = new InfoFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };

    public List<String> getTextFromWeb(String urlString)
    {
        URLConnection feedUrl;
        List<String> placeAddress = new ArrayList<>();

        try
        {
            feedUrl = new URL(urlString).openConnection();
            InputStream is = feedUrl.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = null;

            while ((line = reader.readLine()) != null) // read line by line
            {
                placeAddress.add(line); // add line to list
            }
            is.close(); // close input stream

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
                                final List<String> addressList = getTextFromWeb("http://localhost/ochslu/");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                        for (int i=0; i<addressList.size()/29;i++)
                            myDB.insertData(addressList.get(i*29),addressList.get(i*29+1),addressList.get(i*29+2),addressList.get(i*29+3),addressList.get(i*29+4),addressList.get(i*29+5),
                                addressList.get(i*29+6),addressList.get(i*29+7),addressList.get(i*29+8),addressList.get(i*29+9),addressList.get(i*29+10),addressList.get(i*29+11),
                                addressList.get(i*29+12),addressList.get(i*29+13),addressList.get(i*29+14),addressList.get(i*29+15),addressList.get(i*29+16),addressList.get(i*29+17),
                                addressList.get(i*29+18),addressList.get(i*29+19),addressList.get(i*29+20),addressList.get(i*29+21),addressList.get(i*29+22),addressList.get(i*29+23),
                                addressList.get(i*29+24),addressList.get(i*29+25),addressList.get(i*29+26),addressList.get(i*29+27),addressList.get(i*29+28));

                                        //update ui
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
