package com.example.aloel.susu_panas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aloel on 5/8/2017.
 */

public class database extends SQLiteOpenHelper {

    public static final int MYDATABASE_VERSION = 1;
    public static final String MYDATABASE_NAME = "toko";

    //=====================================TABEL barang baru============================================================//
    public static final String MYDATABASE_TABLE = "barang_baru";
    public static final String KEY_id = "id";
    public static final String KEY_nama = "nama";
    public static final String KEY_stock = "stock";
    public static final String KEY_tanggal = "tanggal";
    private static final String SCRIPT_CREATE_TABLE =
            "create table " + MYDATABASE_TABLE + " ("
                    + KEY_id + " integer primary key autoincrement, "
                    + KEY_nama + " text not null, "
                    + KEY_stock + " integer not null, "
                    + KEY_tanggal + " text not null);";

    //-------------DEKLARASI UNTUK MENGHAPUS TABEL-------------//
    private static final String SCRIPT_DELETE_TABLE = "DROP TABLE IF EXISTS " + MYDATABASE_TABLE;


    public database(Context context) {
        //BUAT DATABASE JIKA TIDAK ADA
        super(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        //BUAT TABEL
        db.execSQL(SCRIPT_CREATE_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int olv, int newv) {
        db.execSQL(SCRIPT_DELETE_TABLE);
        onCreate(db);
    }

    public void insertLabel(String label) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_nama, label);

    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MYDATABASE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public String getKEY_stock(String nama_barang) {

        String selectQuery = "SELECT  * FROM " + MYDATABASE_TABLE + " where " + KEY_nama + " = '" + nama_barang+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        String theStock = "0";
        if (cursor.moveToFirst()) {
            do {
                theStock = cursor.getString(2);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        return theStock;
    }
}
