package com.example.ochronasuchu;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ItemHearingProtector> listProtection = new ArrayList<>();
    private ArrayList<ItemHearingProtector> listProtectionUser = new ArrayList<>();
    private UpdateInterface listener;
    String addType = "N";
    Boolean dataInserted = false;
    Integer deletedRows = 0;


    LocalDate currentDatabaseTime = LocalDate.of(2020,5,27);
    LocalDate stockDatabaseTime = LocalDate.of(2020,5,27);
    LocalDate onlineDatabaseTime = LocalDate.of(2020,5,27);
    List<String> onlineDataInput = null;

    public LocalDate getCurrentDatabaseTime() {
        return currentDatabaseTime;
    }
    public LocalDate getStockDatabaseTime() {
        return stockDatabaseTime;
    }
    public LocalDate getOnlineDatabaseTime() {
        return onlineDatabaseTime;
    }
    public void setCurrentDatabaseTime(LocalDate currentDatabaseTime) {
        this.currentDatabaseTime = currentDatabaseTime;
    }
    public void setOnlineDatabaseTime(LocalDate onlineDatabaseTime) {
        this.onlineDatabaseTime = onlineDatabaseTime;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //odczytanie z bd


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            installDefaultDB();
        }
        writeRecords();
        writeRecordsUser();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(naviListener);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Przejście do ekranu powitalnego (mało eleganckie ale działa)
        FragmentWelcome wlc_fragment = new FragmentWelcome();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, wlc_fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                showDialogAdd();
                addType = "N";
                return true;
            case R.id.item3:
                sortRecordsByProd();
                sortUserRecordsByProd();
                refreshRecycler();
                return true;
            case R.id.item4:
                sortRecordsByH();
                sortUserRecordsByH();
                refreshRecycler();
                return true;
            case R.id.item5:
                sortRecordsByM();
                sortUserRecordsByM();
                refreshRecycler();
                return true;
            case R.id.item6:
                sortRecordsByL();
                sortUserRecordsByL();
                refreshRecycler();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void resetFragmentInfo(){
        FragmentInfo fragment = new FragmentInfo();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener naviListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_baza:
                            selectedFragment = new FragmentBase();
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("bundle_key",listProtection);
                            selectedFragment.setArguments(bundle);
                            setListener((UpdateInterface) selectedFragment);
                            break;
                        case R.id.navigation_moje:
                            selectedFragment = new FragmentUser();
                            Bundle bundle2 = new Bundle();
                            bundle2.putParcelableArrayList("bundle_key",  listProtectionUser);
                            selectedFragment.setArguments(bundle2);
                            setListener((UpdateInterface) selectedFragment);
                            break;
                        case R.id.navigation_info:
                            selectedFragment = new FragmentInfo();
                            break;
                    }

                    assert selectedFragment != null;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    // metoda odczytuje z bazy danych i wpisuje do arraylist
    public void writeRecords() {
        DatabaseHelper myDB = new DatabaseHelper(this);
        if (listProtection != null) {
            listProtection.clear();
        } else {
            listProtection = new ArrayList<>();
        }

        Cursor cursor = myDB.getAllData();
        try {
            while (cursor.moveToNext()) {
                listProtection.add(new ItemHearingProtector(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
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

        Fragment fragment = new FragmentBase();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundle_key", listProtection);
        fragment.setArguments(bundle);
    }

    public void writeRecordsUser() {
        DatabaseHelper myDB = new DatabaseHelper(this);
        if (listProtectionUser != null) {
            listProtectionUser.clear();
        } else {
            listProtectionUser = new ArrayList<>();
        }

        Cursor cursor = myDB.getAllData();
        try {
            while (cursor.moveToNext()) {
                if (cursor.getString(29).equals("user")) {
                    listProtectionUser.add(new ItemHearingProtector(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
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

        Fragment fragment = new FragmentUser();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundle_key", listProtectionUser);
        fragment.setArguments(bundle);
    }

    public void showDialogAdd() {
        DialogAddItem dialogAddItem = new DialogAddItem();
        dialogAddItem.show(getSupportFragmentManager(), "add dialog");

    }

    public void showDialogResetDatabase(){
        DialogResetDatabase resetDialog = new DialogResetDatabase();
        resetDialog.show(getSupportFragmentManager(),"reset dialog");
    }

    public void showDialogUpdateDatabase(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUpdateDatabase updateDialog = new DialogUpdateDatabase();
                updateDialog.show(getSupportFragmentManager(), "update dialog");
             }
        });
    }



    //metody do sortowania
    public void sortRecordsByProd() {
        Collections.sort(listProtection, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o1, ItemHearingProtector o2) {
                return o1.getProd().compareTo(o2.getProd());
            }
        });
    }
    public void sortUserRecordsByProd() {
        Collections.sort(listProtectionUser, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o1, ItemHearingProtector o2) {
                return o1.getProd().compareTo(o2.getProd());
            }
        });
    }
    public void sortRecordsByH() {
        Collections.sort(listProtection, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o2, ItemHearingProtector o1) {
                return Integer.parseInt(o1.getH()) - Integer.parseInt(o2.getH());
            }
        });
    }
    public void sortUserRecordsByH() {
        Collections.sort(listProtectionUser, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o2, ItemHearingProtector o1) {
                return Integer.parseInt(o1.getH()) - Integer.parseInt(o2.getH());
            }
        });
    }
    public void sortRecordsByM() {
        Collections.sort(listProtection, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o2, ItemHearingProtector o1) {
                return Integer.parseInt(o1.getM()) - Integer.parseInt(o2.getM());
            }
        });
    }
    public void sortUserRecordsByM() {
        Collections.sort(listProtectionUser, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o2, ItemHearingProtector o1) {
                return Integer.parseInt(o1.getM()) - Integer.parseInt(o2.getM());
            }
        });
    }
    public void sortRecordsByL() {
        Collections.sort(listProtection, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o2, ItemHearingProtector o1) {
                return Integer.parseInt(o1.getL()) - Integer.parseInt(o2.getL());
            }
        });
    }
    public void sortUserRecordsByL() {
        Collections.sort(listProtectionUser, new Comparator<ItemHearingProtector>() {
            @Override
            public int compare(ItemHearingProtector o2, ItemHearingProtector o1) {
                return Integer.parseInt(o1.getL()) - Integer.parseInt(o2.getL());
            }
        });
    }

    public void setListener(UpdateInterface listener) {
        this.listener = listener;
    }

    public void refreshRecycler() {
        if (listener !=null) {
            listener.updateRecycler();
        }
    }
    //Metoda odczytuje stronę internetową i zapisuje w liście
    public List<String> getTextFromWeb(String urlString) {
        //URLConnection feedUrl;
        List<String> placeAddress;
        final StringBuilder builder = new StringBuilder();
        try {

            Document doc = Jsoup.connect(urlString).get();
            Element body = doc.body();
            builder.append(body.text());
            String separate_records[] = String.valueOf(builder).split(" @@@ ");
            placeAddress = Arrays.asList(separate_records);
            return placeAddress; // return whatever you need
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Metoda pobiera dane ze strony internetowej i wpisuje do bazy danych
    public void downloadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                onlineDataInput = getTextFromWeb("https://goraceochronnikisluchu.pl/ochronniki/");
                if (onlineDataInput != null) {
                    setOnlineDatabaseTime(LocalDate.parse(onlineDataInput.get(0), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    showDialogUpdateDatabase();
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Brak połączenia z serwerem", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        }).start();

    }
    public void updateData(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                DatabaseHelper myDB = new DatabaseHelper(getApplicationContext());
                if (onlineDataInput != null) {
                    deletedRows = myDB.deleteWebData();
                    // i=0 to data, i=1 to typ, i = 29 to certyfikat, i = 30 to typ
                    for (int i = 0; i < onlineDataInput.size() / 29; i++)
                        myDB.insertData(onlineDataInput.get(i * 29 + 1), onlineDataInput.get(i * 29 + 2), onlineDataInput.get(i * 29 + 3), onlineDataInput.get(i * 29 + 4), onlineDataInput.get(i * 29 + 5),
                                onlineDataInput.get(i * 29 + 6), onlineDataInput.get(i * 29 + 7), onlineDataInput.get(i * 29 + 8), onlineDataInput.get(i * 29 + 9), onlineDataInput.get(i * 29 + 10), onlineDataInput.get(i * 29 + 11),
                                onlineDataInput.get(i * 29 + 12), onlineDataInput.get(i * 29 + 13), onlineDataInput.get(i * 29 + 14), onlineDataInput.get(i * 29 + 15), onlineDataInput.get(i * 29 + 16), onlineDataInput.get(i * 29 + 17),
                                onlineDataInput.get(i * 29 + 18), onlineDataInput.get(i * 29 + 19), onlineDataInput.get(i * 29 + 20), onlineDataInput.get(i * 29 + 21), onlineDataInput.get(i * 29 + 22), onlineDataInput.get(i * 29 + 23),
                                onlineDataInput.get(i * 29 + 24), onlineDataInput.get(i * 29 + 25), onlineDataInput.get(i * 29 + 26), onlineDataInput.get(i * 29 + 27), onlineDataInput.get(i * 29 + 28),onlineDataInput.get(i * 29 + 29));
                    dataInserted = true;
                }
                myDB.close();
                if (deletedRows > 0 && dataInserted) {
                    Toast.makeText(getApplicationContext(), "Aktualizacja ukończona pomyślnie", Toast.LENGTH_SHORT).show();
                } else if (deletedRows > 0 && !dataInserted) {
                    Toast.makeText(getApplicationContext(), "Usunieto i nie dodano", Toast.LENGTH_SHORT).show();
                } else if (deletedRows == 0 && dataInserted) {
                    Toast.makeText(getApplicationContext(), "Pomyślnie dodano", Toast.LENGTH_SHORT).show();
                } else if (deletedRows == 0 && !dataInserted) {
                    Toast.makeText(getApplicationContext(), "Brak połączenia z serwerem", Toast.LENGTH_SHORT).show();
                }
                dataInserted = false;
            }
        });
    }


    public void addToDatabase(ArrayList<String> list) {
        DatabaseHelper myDB = new DatabaseHelper(getApplicationContext());
        if (list != null) {
            myDB.insertData(addType, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12),
                    list.get(13), list.get(14), list.get(15), list.get(16), list.get(17), list.get(18), list.get(19), list.get(20), list.get(21), list.get(22), list.get(23), list.get(24),
                    list.get(25), list.get(26), "user");

        }

        myDB.close();
    }

    public void deleteFromDatabase(ArrayList<String> list) {
        DatabaseHelper myDB = new DatabaseHelper(getApplicationContext());
        if (list != null) {
            myDB.deleteRecord(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7), list.get(8), list.get(9), list.get(10),
                    list.get(11), list.get(12), list.get(13), list.get(14), list.get(15), list.get(16), list.get(17), list.get(18), list.get(19), list.get(20), list.get(21));
        }
        myDB.close();
    }

    public void clearDatabase() {
        DatabaseHelper myDB = new DatabaseHelper(getApplicationContext());
        myDB.deleteWebData();
        myDB.close();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_nausznik:
                addType = "N";
                break;
            case R.id.radio_wkladka:
                if (checked)
                    addType = "W";
                break;
        }


    }

    //metoda uruchomi się tylko raz po instalacji aplikacji
    //Podczas testów nieelegacko będzie tu podana baza danych, później możliwa zamiana na pobranie jej z internetu
    public void installDefaultDB() {
        currentDatabaseTime = stockDatabaseTime;
        DatabaseHelper myDB = new DatabaseHelper(getApplicationContext());
        myDB.deleteWebData();
        myDB.insertData("W", "TON INSTITUTE ", "TP_01", "27.7", "28.5", "31.1", "29.1", "32.9", "40.8", "43.5", "6.3", "5.5", "7.4", "4.9", "3.1", "7.1", "4", "21.4", "23", "23.7", "24.2", "29.8", "33.7", "39.5", "30", "26", "24", "29", "UE/117/2019/1437");
        myDB.insertData("W", "TON INSTITUTE ", "TP_02", "10.1", "13.8", "17.5", "22.4", "27.1", "33", "31.8", "3.3", "4.2", "3", "3.8", "3.1", "6.4", "4.6", "6.8", "9.6", "14.5", "18.6", "24", "26.6", "27.2", "24", "17", "12", "20", "UE/118/2019/1437");
        myDB.insertData("W", "TON INSTITUTE ", "TP_03", "13.2", "17.1", "20.3", "24.3", "27.2", "30.2", "39.5", "6.5", "4.8", "4.9", "4.9", "3.8", "7.3", "6.7", "6.7", "12.3", "15.4", "19.4", "23.4", "22.9", "32.8", "23", "18", "13", "21", "UE/119/2019/1437");
        myDB.insertData("W", "TON INSTITUTE ", "TP_04", "13.3", "14.6", "15.3", "19", "26.8", "27.2", "30.3", "4.6", "4.3", "3.9", "4.1", "2.7", "4.6", "5.7", "8.7", "10.3", "11.4", "14.9", "24.1", "22.6", "24.6", "22", "15", "12", "18", "UE/120/2019/1437");
        myDB.insertData("N", "3M", "Peltor H520A", "11.4", "18.7", "27.5", "32.9", "33.6", "36.6", "35.9", "4.1", "3.6", "2.5", "2.7", "3.4", "2.7", "3.7", "7.3", "15.1", "25", "30.6", "32.5", "33.6", "32.7", "32", "25", "15", "27", "web");
        myDB.insertData("N", "3M", "Peltor H520A", "14.6", "20.2", "32.5", "39.3", "36.4", "34.4", "40.2", "1.6", "2.5", "2.3", "2.1", "2.4", "4", "2.3", "13", "17.7", "30.2", "37.1", "34", "30.4", "37.9", "34", "29", "20", "31", "web");
        myDB.insertData("N", "3M", "Peltor H540A", "17.4", "24.7", "34.7", "41.4", "39.3", "47.5", "42.6", "2.1", "2.6", "2", "2.1", "1.5", "4.5", "2.6", "15.3", "22.1", "32.7", "39.3", "37.8", "43", "40", "40", "32", "23", "35", "web");
        myDB.insertData("N", "3M", "Peltor X1A", "11.9", "15.4", "24.5", "34.3", "32.6", "37.4", "37.4", "2", "2.6", "2.6", "2.4", "3.3", "2.5", "3.8", "9.9", "12.8", "21.9", "31.9", "29.3", "34.9", "33.6", "32", "24", "16", "27", "web");
        myDB.insertData("N", "3M", "Peltor X2A", "14.1", "22.2", "31.1", "39.7", "36.6", "37", "37.9", "2.2", "2.1", "2.7", "3.2", "3.2", "3.7", "3.4", "11.9", "20.1", "28.4", "36.5", "33.4", "33.3", "34.5", "34", "29", "20", "31", "web");
        myDB.insertData("N", "3M", "Peltor X3A", "22.8", "25.1", "27", "40", "35.8", "38.5", "38.9", "2.1", "3.1", "1.7", "2.8", "2.2", "2.7", "2.9", "20.7", "22", "25.3", "37.2", "33.6", "35.8", "36", "35", "30", "25", "33", "web");
        myDB.insertData("N", "3M", "Peltor X4A", "17.8", "22.1", "30.6", "39.5", "37.3", "43.8", "42.1", "2.3", "2.5", "1.8", "2.9", "4.1", "2.8", "4", "15.5", "19.6", "28.8", "36.6", "33.2", "41", "38.1", "36", "30", "22", "33", "web");
        myDB.insertData("N", "3M", "Peltor X5A", "22.3", "28.8", "39.7", "44.2", "39.8", "43", "40.2", "2.4", "2.4", "2.7", "3.4", "4.6", "2.8", "2.9", "19.9", "26.4", "37", "40.8", "35.2", "40.2", "37.3", "37", "35", "27", "37", "web");
        myDB.insertData("N", "3M", "Peltor H520P3", "14.1", "19.4", "32", "39.9", "36.2", "35.4", "39.2", "2.3", "2.7", "2.7", "2.4", "2.6", "4.4", "2.6", "11.8", "16.7", "29.3", "37.5", "33.6", "31", "36.6", "34", "28", "19", "30", "web");
        myDB.insertData("N", "Honeywell", "Howard Leight Leightning L1", "20.3", "22.9", "28.3", "32.9", "32.3", "39.3", "35.1", "2.5", "2.8", "1.7", "2.9", "3.8", "2.8", "4", "17.8", "20.1", "26.6", "30", "28.5", "36.5", "31.1", "31", "28", "23", "30", "web");
        myDB.insertData("N", "Honeywell", "Howard Leight Leightning L2", "20.1", "24.5", "29.3", "34.4", "32.4", "35.9", "35.6", "4", "2.9", "3.2", "2.6", "3", "2.6", "3.2", "16.1", "21.6", "26.1", "31.8", "29.4", "33.3", "32.4", "31", "29", "23", "31", "web");
        myDB.insertData("N", "Honeywell", "Howard Leight Leightning L3", "24.6", "27.8", "32.6", "27.4", "35.2", "38.8", "35.8", "3.6", "2", "2", "3.3", "3.2", "3.1", "3.3", "21", "25.8", "30.6", "24.1", "32", "35.7", "32.5", "33", "32", "27", "34", "web");
        myDB.insertData("N", "Honeywell", "Howard Leight Thunder T3", "23.6", "30.8", "34.6", "40.3", "38.3", "43.1", "40.3", "5.3", "4.5", "3", "2.2", "3.4", "3.4", "3.6", "18.3", "26.3", "31.6", "38.1", "34.9", "39.7", "36.7", "37", "34", "26", "36", "web");
        myDB.insertData("N", "Honeywell", "Howard Leight Leightning L1H", "17.6", "21.6", "25.1", "32.6", "32.9", "36.6", "35.5", "3.8", "3.9", "4.4", "3.4", "3.1", "4.8", "3.9", "13.8", "17.7", "20.7", "29.2", "29.8", "31.8", "31.6", "31", "25", "19", "28", "web");
        myDB.insertData("N", "Honeywell", "Howard Leight Viking V1", "14.1", "20.6", "25.8", "32", "32.1", "33.7", "34.4", "2.3", "3.1", "2.5", "2.8", "2.5", "3.1", "2.5", "11.8", "17.5", "23.3", "29.2", "29.6", "30.6", "31.9", "32", "28", "21", "30", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Laser Lite", "34.1", "35.5", "37.6", "34.9", "35.7", "42.5", "44.1", "4.7", "4.6", "4.1", "5", "2.8", "2.9", "4.2", "29.4", "30.9", "33.5", "29.9", "32.9", "39.6", "39.9", "34", "32", "31", "35", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Matrix", "21.8", "26.1", "28.7", "29.5", "34.9", "37.2", "39.8", "4.7", "5.4", "5.2", "5.3", "3.8", "2.7", "4", "17.1", "20.7", "23.5", "24.2", "31.1", "34.5", "35.8", "31", "25", "22", "29", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Laser Firmfit", "35.4", "32.4", "38.2", "37.2", "39.2", "45.9", "45.7", "5.2", "4.3", "4.8", "3.4", "3.4", "4.4", "5.4", "30.2", "28.1", "33.4", "33.8", "35.8", "41.5", "40.3", "37", "34", "31", "37", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Max Lite", "35.5", "36.7", "39", "37.4", "33.8", "41.9", "43.3", "6.3", "7.1", "6.6", "6.1", "3.7", "3.8", "4.7", "29.2", "29.6", "32.4", "31.3", "30.1", "38.1", "38.6", "32", "32", "31", "34", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Max", "37.1", "37.4", "38.8", "38.2", "37.9", "47.3", "44.8", "4.5", "4.3", "3.7", "3.5", "4", "3.5", "7.2", "32.6", "33.1", "35.1", "34.7", "33.9", "43.8", "37.6", "36", "35", "34", "37", "web");
        myDB.insertData("N", "Hellberg", "Hellberg Secure 1H", "10.9", "15.6", "25.4", "31.1", "30.8", "33.8", "33.5", "2.3", "2.2", "3.4", "3", "2.3", "3.1", "2.6", "8.6", "13.4", "22", "28.1", "28.5", "30.7", "30.9", "30", "23", "15", "26", "web");
        myDB.insertData("N", "Hellberg", "Hellberg Secure 2H", "13.6", "21.8", "30.7", "39.4", "35.8", "37.6", "40", "3.4", "2.7", "3.1", "3", "2.9", "2.8", "4.8", "10.2", "19.1", "27.6", "36.4", "32.9", "34.8", "35.2", "35", "28", "18", "30", "web");
        myDB.insertData("N", "Hellberg", "Hellberg Secure 3H", "17.5", "24.8", "32.7", "43.8", "36.4", "35.9", "38.1", "3", "2.4", "2.6", "3.7", "3.5", "3.1", "4.1", "14.5", "22.4", "30.1", "40.1", "32.9", "32.8", "34", "34", "31", "22", "33", "web");
        myDB.insertData("N", "Hellberg", "Hellberg Secure 2C", "15", "21", "28.1", "35.3", "34", "34", "37.3", "2.6", "3.1", "3.5", "4", "3.8", "4.5", "4.4", "12.4", "17.9", "24.6", "31.3", "30.2", "29.5", "32.9", "31", "27", "19", "29", "web");
        myDB.insertData("N", "JSP", "JSP Classis gp", "12.9", "19.4", "26.7", "36.4", "38.9", "40.7", "40.2", "6.1", "4.4", "4.1", "3.6", "3.7", "5.1", "4.4", "6.8", "15", "22.6", "32.8", "35.2", "35.6", "35.8", "35", "24", "15", "27", "web");
        myDB.insertData("N", "JSP", "JSP Economuff", "8.3", "11.4", "19.4", "27.1", "30.4", "32.7", "27.2", "3.1", "2.6", "4.5", "3.1", "4", "3.4", "5.4", "5.2", "8.8", "14.9", "24", "26.4", "29.3", "21.8", "26", "18", "11", "21", "web");
        myDB.insertData("N", "JSP", "JSP J Muff", "10.7", "13.1", "22.4", "31.7", "31.4", "31.5", "30.1", "3.3", "2.7", "2.5", "4.2", "3.6", "3.5", "5.2", "7.4", "10.4", "19.9", "27.5", "27.8", "28", "24.9", "28", "21", "13", "24", "web");
        myDB.insertData("W", "Uvex", "Uvex x-fit", "38.4", "39.3", "40.8", "38.9", "37.8", "45.9", "45.1", "6.3", "5.3", "5.5", "5.5", "4.3", "3.9", "4.6", "32.1", "34", "35.3", "33.4", "33.5", "42", "40.5", "36", "34", "34", "37", "web");
        myDB.insertData("W", "Uvex", "Uvex com4-fit", "31", "33.6", "33.8", "33.6", "36.2", "40.6", "44.3", "5.2", "5.4", "5.4", "4", "4.2", "3.7", "2.9", "25.8", "28.2", "28.4", "29.6", "32", "36.9", "41.4", "33", "30", "29", "33", "web");
        myDB.insertData("W", "Uvex", "Uvex hi-com lime", "20", "20.4", "22.8", "24.6", "31.2", "37.5", "40.3", "2.9", "3.2", "4.7", "5.1", "5", "5.9", "6.6", "17.1", "17.2", "18.1", "19.5", "26.2", "31.6", "33.7", "26", "20", "18", "24", "web");
        myDB.insertData("W", "Uvex", "Uvex Whisper", "23.2", "21.1", "22.6", "24.8", "31.7", "33.4", "39.4", "5.8", "6.8", "5.2", "5.7", "6", "9.5", "8.2", "17.4", "14.3", "17.4", "19.1", "25.7", "23.9", "31.2", "24", "20", "17", "23", "web");
        myDB.insertData("W", "Uvex", "Uvex x-fold", "23.8", "20.5", "18.5", "21.6", "31.3", "36.3", "39.4", "3.2", "3.4", "4.7", "2.6", "3.6", "2.5", "3.3", "20.6", "17.1", "13.8", "19", "27.7", "33.8", "36.1", "26", "18", "17", "23", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Airsoft", "29.8", "28.6", "30.5", "32.5", "33.6", "35.4", "39.1", "5", "5.6", "5.5", "4.3", "4.2", "7.2", "4.6", "24.8", "23", "25", "28.2", "29.4", "28.2", "34.5", "29", "27", "25", "30", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Smartfit", "31.4", "28.8", "32.5", "33.8", "35.6", "39.3", "41.9", "7.3", "8.9", "8.1", "7.3", "4.3", "6", "5", "24.1", "19.9", "24.4", "26.5", "31.3", "33.3", "36.9", "32", "27", "23", "30", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Neutron", "18.8", "17.8", "19.3", "25.3", "29.1", "25.6", "20.3", "8.4", "6.4", "5.1", "5.1", "3.8", "4.7", "3", "10.4", "11.4", "14.2", "20.2", "25.3", "20.9", "17.3", "21", "18", "14", "20", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Fusion", "28.3", "28.6", "27.9", "29.4", "31", "40", "40.9", "5.1", "5.6", "5", "5.6", "3.7", "5.6", "5.5", "23.2", "23", "22.9", "23.8", "27.3", "34.4", "35.4", "29", "25", "24", "28", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight PerCap", "22.5", "21.5", "19", "22.6", "30.3", "35.7", "38.8", "3.5", "3.6", "2.9", "2.7", "3.1", "4.2", "4.3", "19", "17.9", "16.1", "19.9", "27.2", "31.5", "34.5", "27", "20", "18", "24", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight QB3", "22.3", "20.6", "16.8", "22.7", "30.6", "34.2", "33.7", "4.2", "3.7", "2.4", "4", "3.5", "3.8", "6.1", "18.1", "16.9", "14.4", "18.7", "27.1", "30.4", "27.6", "25", "19", "17", "23", "web");
        myDB.insertData("W", "Honeywell", "Howard Leight Bilsom 303", "37.3", "37.9", "39.1", "36", "34.6", "42.5", "46.4", "9", "9.2", "9.7", "7.9", "4.6", "4.9", "4.7", "28.3", "28.7", "29.4", "28.1", "30", "37.6", "41.7", "32", "29", "29", "33", "web");
        myDB.insertData("W", "STANMARK", "Stopper ELA", "25.3", "24", "23.5", "24.1", "29.3", "33.7", "42.9", "4.6", "4.2", "4.4", "3.8", "4", "7.5", "6.4", "20.7", "19.8", "19.1", "20.3", "25.3", "26.2", "36.5", "25", "21", "20", "25", "web");
        myDB.insertData("N", "MSA", "MSA XLS", "11.6", "17.2", "21.7", "30.4", "29.2", "35.4", "34.4", "3.5", "2.7", "3.1", "3.4", "4.2", "4.1", "4.6", "8.1", "14.5", "18.6", "27", "25", "31.3", "29.8", "27", "22", "15", "25", "web");
        myDB.insertData("N", "MSA", "MSA EXC", "11.2", "19.1", "25.7", "29.2", "32", "36.8", "39", "3.2", "2.2", "2.7", "3.1", "2.3", "2.7", "3.7", "8", "16.9", "23", "26.1", "29.7", "34.1", "35.3", "31", "24", "16", "27", "web");
        myDB.insertData("N", "MSA", "MSA HPE", "20.6", "23.7", "29.7", "35", "33.1", "39", "41.7", "3.4", "1.9", "3", "3.3", "3.3", "3", "4", "17.2", "21.8", "26.7", "31.7", "29.8", "36", "37.7", "32", "29", "23", "32", "web");
        myDB.insertData("W", "3M", "3M EARsoft", "30.8", "36.1", "39.2", "39.5", "35.8", "42.1", "46.1", "6.5", "6.7", "4.7", "3.9", "4.9", "3.1", "3.3", "24.3", "29.4", "34.5", "35.6", "30.9", "39", "42.8", "34", "34", "31", "36", "web");
        myDB.insertData("W", "3M", "3M 1271", "27.7", "28.4", "29.5", "29.6", "35.6", "35.4", "38.9", "9.9", "10.9", "9.6", "8.2", "6.8", "9.6", "6.7", "17.8", "17.5", "19.9", "21.4", "28.8", "25.8", "32.2", "27", "22", "20", "25", "web");
        myDB.insertData("W", "3M", "3M 1310", "21.7", "21.8", "23.6", "25.1", "34.8", "40.5", "42.7", "4.6", "4.5", "4.3", "3", "3.2", "4.3", "3.6", "17.1", "17.3", "19.3", "22.1", "31.6", "36.2", "39.1", "30", "22", "19", "26", "web");
        myDB.insertData("W", "3M", "3M 1120", "27.3", "30.8", "33.5", "36.5", "39", "46.9", "45.3", "5.4", "5.6", "5.9", "4", "3.7", "4.7", "4.6", "21.9", "25.2", "27.6", "32.5", "35.3", "42.2", "40.7", "37", "31", "27", "34", "web");
        myDB.insertData("W", "3M", "3M 1100", "33.1", "36.3", "38.4", "38.7", "39.7", "48.3", "44.4", "5", "7.4", "6.2", "5.6", "4.3", "4.5", "4.4", "28.1", "28.9", "32.2", "33.1", "35.4", "43.8", "40", "37", "34", "31", "37", "web");
        myDB.insertData("W", "3M", "3M EAR Classic", "23.3", "24.6", "26.9", "27.4", "34.1", "41.6", "40.4", "5.3", "3.6", "5.4", "4.8", "3.1", "3.5", "6.4", "18", "21", "21.5", "22.6", "31", "38.1", "34", "30", "24", "22", "28", "web");
        myDB.insertData("W", "3M", "3M EAR Band", "19.4", "16", "16.5", "20.9", "31.4", "35.3", "36", "5.4", "4.1", "4.2", "2.5", "4.3", "3.6", "4", "14", "11.9", "12.3", "18.4", "27.1", "31.7", "32", "25", "17", "14", "21", "web");
        myDB.insertData("W", "3M", "3M EAR Ultrafit 14", "2.9", "4.3", "8.3", "18.3", "26.9", "31.4", "29.9", "1.9", "1.7", "3", "2.2", "2.2", "3.4", "3.9", "1", "2.6", "5.3", "16.1", "24.7", "28", "26", "22", "10", "5", "14", "web");
        myDB.insertData("W", "3M", "3M EAR Ultrafit", "29.4", "29.4", "32.2", "32.3", "36.1", "44.3", "44.8", "7.4", "6.6", "5.3", "5", "3.2", "6", "6.4", "22", "22.8", "26.9", "27.3", "32.9", "38.3", "38.4", "33", "28", "25", "32", "web");
        myDB.insertData("W", "3M", "3M EAR Ultrafit 20", "9", "11.9", "17.6", "23.9", "28.9", "32.1", "35.8", "4.5", "3.9", "3.6", "3.1", "3.6", "7.1", "4.2", "4.5", "8", "14", "20.8", "25.3", "25", "31.6", "25", "17", "10", "20", "web");
        myDB.insertData("W", "3M", "3M EARsoft FX", "37.5", "38.5", "40.4", "38.6", "39.6", "48.9", "47.8", "6", "5.4", "5", "4.2", "2.5", "3.8", "3.9", "31.5", "33.1", "35.4", "34.4", "37.1", "45.1", "43.9", "39", "36", "34", "39", "web");

        myDB.close();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }


}
