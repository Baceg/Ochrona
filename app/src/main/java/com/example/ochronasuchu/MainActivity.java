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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public DatabaseHelper myDB;
    private ArrayList<ItemDto> listaOchron;
    private ArrayList<ItemDto> listaOchronUser;
    private UpdateInterface listener;

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
        setListner(bz_fragment);
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
                listener.updateData();
                return true;
            case R.id.item4:
                sortRecordsByH();
                sortUserRecordsByH();
                listener.updateData();
                return true;
            case R.id.item5:
                sortRecordsByM();
                sortUserRecordsByM();
                listener.updateData();
                return true;
            case R.id.item6:
                sortRecordsByL();
                sortUserRecordsByL();
                listener.updateData();
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
                            setListner((UpdateInterface) selectedFragment);
                            break;
                        case R.id.navigation_moje:
                            selectedFragment = new MojeFragment();
                            Bundle bundle2 = new Bundle();
                            bundle2.putSerializable("bundle_key", listaOchronUser);
                            selectedFragment.setArguments(bundle2);
                            setListner((UpdateInterface) selectedFragment);
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

    public void setListner(UpdateInterface listener){
        this.listener = listener;
    }

}
