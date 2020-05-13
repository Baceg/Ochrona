package com.example.ochronasuchu;

import android.database.Cursor;
import android.os.Bundle; //nw co robi
import androidx.annotation.NonNull; //nw co robi
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.InetAddress;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public DatabaseHelper myDB;
    private ArrayList<ItemDto> listaOchron;
    private ArrayList<ItemDto> listaOchronUser;
    private UpdateInterface listener;

    Boolean dataInserted = false;
    Integer deletedRows = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeRecords();
        writeRecordsUser();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(naviListener);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //żeby wyjść z białegóm gówna to wziąć

        BazaFragment bz_fragment = new BazaFragment();
        setListener(bz_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,bz_fragment).commit();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundle_key", listaOchron);
        bz_fragment.setArguments(bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                //showDialogAdd();
                writeRecords();
                writeRecordsUser();

                return true;
            case R.id.item3:
                sortRecordsByProd();
                sortUserRecordsByProd();
                listener.updateRecycler();
                return true;
            case R.id.item4:
                sortRecordsByH();
                sortUserRecordsByH();
                listener.updateRecycler();
                return true;
            case R.id.item5:
                sortRecordsByM();
                sortUserRecordsByM();
                listener.updateRecycler();
                return true;
            case R.id.item6:
                sortRecordsByL();
                sortUserRecordsByL();
                listener.updateRecycler();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener naviListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_baza:
                            selectedFragment = new BazaFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("bundle_key", listaOchron);
                            selectedFragment.setArguments(bundle);
                            setListener((UpdateInterface) selectedFragment);
                            break;
                        case R.id.navigation_moje:
                            selectedFragment = new MojeFragment();
                            Bundle bundle2 = new Bundle();
                            bundle2.putSerializable("bundle_key", listaOchronUser);
                            selectedFragment.setArguments(bundle2);
                            setListener((UpdateInterface) selectedFragment);
                            break;
                        case R.id.navigation_info:
                            selectedFragment = new InfoFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };

//funkcja zamieniająca fragment
    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void writeRecords() {
        myDB = new DatabaseHelper(this);
        listaOchron = new ArrayList<>();
        Cursor cursor = myDB.getAllData();
        try {
            while (cursor.moveToNext()) {
                listaOchron.add(new ItemDto(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                        cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17),
                        cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23),
                        cursor.getString(24), cursor.getString(25), cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29)));
            }
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        myDB.close();
    }

    public void writeRecordsUser() {
        myDB = new DatabaseHelper(this);
        listaOchronUser = new ArrayList<>();
        Cursor cursor = myDB.getAllData();
        try {
            while (cursor.moveToNext()) {
                if (cursor.getString(29).toString().equals("user")) {
                    listaOchronUser.add(new ItemDto(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11),
                            cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17),
                            cursor.getString(18), cursor.getString(19), cursor.getString(20), cursor.getString(21), cursor.getString(22), cursor.getString(23),
                            cursor.getString(24), cursor.getString(25), cursor.getString(26), cursor.getString(27), cursor.getString(28), cursor.getString(29)));
                }
            }
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        myDB.close();
    }

    public void sortRecordsByProd(){
        Collections.sort(listaOchron, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o1, ItemDto o2) {
                return o1.getProd().compareTo(o2.getProd());
            }
        });
    }

    public void sortUserRecordsByProd(){
        Collections.sort(listaOchronUser, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o1, ItemDto o2) {
                return o1.getProd().compareTo(o2.getProd());
            }
        });
    }
    public void sortRecordsByH(){
        Collections.sort(listaOchron, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o2, ItemDto o1) {
                return Integer.parseInt(o1.getH())-Integer.parseInt(o2.getH());
            }
        });
    }

    public void sortUserRecordsByH(){
        Collections.sort(listaOchronUser, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o2, ItemDto o1) {
                return Integer.parseInt(o1.getH())-Integer.parseInt(o2.getH());
            }
        });
    }

    public void sortRecordsByM(){
        Collections.sort(listaOchron, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o2, ItemDto o1) {
                return Integer.parseInt(o1.getM())-Integer.parseInt(o2.getM());
            }
        });
    }

    public void sortUserRecordsByM(){
        Collections.sort(listaOchronUser, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o2, ItemDto o1) {
                return Integer.parseInt(o1.getM())-Integer.parseInt(o2.getM());
            }
        });
    }

    public void sortRecordsByL(){
        Collections.sort(listaOchron, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o2, ItemDto o1) {
                return Integer.parseInt(o1.getL())-Integer.parseInt(o2.getL());
            }
        });
    }

    public void sortUserRecordsByL(){
        Collections.sort(listaOchronUser, new Comparator<ItemDto>() {
            @Override
            public int compare(ItemDto o2, ItemDto o1) {
                return Integer.parseInt(o1.getL())-Integer.parseInt(o2.getL());
            }
        });
    }

    public void setListener(UpdateInterface listener){
        this.listener = listener;
    }




    public boolean isInternetAvailable(String urlString) {
        try {
            InetAddress ipAddr = InetAddress.getByName(urlString);
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
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
            String separate_records[] = String.valueOf(builder).split(" @@@ ");
            placeAddress = Arrays.asList(separate_records);
            return placeAddress; // return whatever you need
            //}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void updateData(){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final List<String> addressList = getTextFromWeb("http://10.0.2.2/ochslu/");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        //dataInserted = false;
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
                                            Toast.makeText(getApplicationContext(), "Aktualizacja ukończona pomyślnie", Toast.LENGTH_SHORT).show();
                                        } else if (deletedRows > 0 && dataInserted == false ) {
                                            Toast.makeText(getApplicationContext(), "Usunieto i nie dano", Toast.LENGTH_SHORT).show();
                                        }
                                        else if (deletedRows == 0 && dataInserted == true) {
                                            Toast.makeText(getApplicationContext(), "Pomyślnie dodano", Toast.LENGTH_SHORT).show();
                                        }
                                        else if (deletedRows == 0 && dataInserted == false){
                                            Toast.makeText(getApplicationContext(), "Nie usunieto i nie dano", Toast.LENGTH_SHORT).show();
                                        }
                                        dataInserted = false;
                                    }
                                });

                            }
                        }).start();


    }
}
