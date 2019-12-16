package com.example.ochronasuchu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ochronnik.db";
    public static final String TABLE_NAME = "ochronniki_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "typ";
    public static final String COL_3 = "prod";
    public static final String COL_4 = "model";
    public static final String COL_5 = "Mf125";
    public static final String COL_6 = "Mf250";
    public static final String COL_7 = "Mf500";
    public static final String COL_8 = "Mf1000";
    public static final String COL_9 = "Mf2000";
    public static final String COL_10 = "Mf4000";
    public static final String COL_11 = "Mf8000";
    public static final String COL_12 = "sf125";
    public static final String COL_13 = "sf250";
    public static final String COL_14 = "sf500";
    public static final String COL_15 = "sf1000";
    public static final String COL_16 = "sf2000";
    public static final String COL_17 = "sf4000";
    public static final String COL_18 = "sf8000";
    public static final String COL_19 = "APV125";
    public static final String COL_20 = "APV250";
    public static final String COL_21 = "APV500";
    public static final String COL_22 = "APV1000";
    public static final String COL_23 = "APV2000";
    public static final String COL_24 = "APV4000";
    public static final String COL_25 = "APV8000";
    public static final String COL_26 = "H";
    public static final String COL_27 = "M";
    public static final String COL_28 = "L";
    public static final String COL_29 = "SNR";
    public static final String COL_30 = "certy";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    private static final String SQL_CREATE_ENTRIES = "create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT,typ TEXT,prod TEXT,model TEXT,Mf125 TEXT,Mf250 TEXT,Mf500 TEXT,Mf1000 TEXT,Mf2000 TEXT,Mf4000 TEXT,Mf8000 TEXT," +
            "sf125 TEXT,sf250 TEXT,sf500 TEXT,sf1000 TEXT,sf2000 TEXT,sf4000 TEXT,sf8000 TEXT, APV125 TEXT,APV250 TEXT,APV500 TEXT,APV1000 TEXT,APV2000 TEXT,APV4000 TEXT,APV8000 TEXT," +
            "H TEXT,M TEXT,L TEXT,SNR TEXT, Certyfikat TEXT ) ";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String typ, String prod, String model, String Mf125, String Mf250, String Mf500, String Mf1000, String Mf2000, String Mf4000, String Mf8000,
                              String sf125, String sf250, String sf500, String sf1000, String sf2000, String sf4000, String sf8000,
                              String APV125, String APV250, String APV500, String APV1000, String APV2000, String APV4000, String APV8000,
                              String H, String M, String L, String SNR,String certy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, typ);
        contentValues.put(COL_3, prod);
        contentValues.put(COL_4, model);
        contentValues.put(COL_5, Mf125);
        contentValues.put(COL_6, Mf250);
        contentValues.put(COL_7, Mf500);
        contentValues.put(COL_8, Mf1000);
        contentValues.put(COL_9, Mf2000);
        contentValues.put(COL_10, Mf4000);
        contentValues.put(COL_11, Mf8000);
        contentValues.put(COL_12, sf125);
        contentValues.put(COL_13, sf250);
        contentValues.put(COL_14, sf500);
        contentValues.put(COL_15, sf1000);
        contentValues.put(COL_16, sf2000);
        contentValues.put(COL_17, sf4000);
        contentValues.put(COL_18, sf8000);
        contentValues.put(COL_19, APV125);
        contentValues.put(COL_20, APV250);
        contentValues.put(COL_21, APV500);
        contentValues.put(COL_22, APV1000);
        contentValues.put(COL_23, APV2000);
        contentValues.put(COL_24, APV4000);
        contentValues.put(COL_25, APV8000);
        contentValues.put(COL_26, H);
        contentValues.put(COL_27, M);
        contentValues.put(COL_28, L);
        contentValues.put(COL_29, SNR);
        contentValues.put(COL_30, certy);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Integer deleteNewData(String prod, String model) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "prod = ? and model = ?", new String[]{prod, model});
    }


}

