package com.example.tugasdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBMain extends SQLiteOpenHelper {
    public static final String DBNAME="store.db";
    public static final String TABLENAME="items";
    public static final int VER = 1;
    String query;

    public DBMain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        query = "create table "+TABLENAME+"(id integer primary key, itemName text, itemPrice)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        query = "drop table if exists "+ TABLENAME+"";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
}
