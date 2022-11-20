package com.example.tugasdb;

import static com.example.tugasdb.DBMain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    DBMain dbMain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        dbMain = new DBMain(this);
        //create method
        findid();
        viewData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void viewData() {
        sqLiteDatabase = dbMain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLENAME+"", null);
        ArrayList<Model>modelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String itemName = cursor.getString(1);
            String itemPrice = cursor.getString(2);
            modelArrayList.add(new Model(id, itemName, itemPrice));
        }
        cursor.close();
        itemAdapter = new ItemAdapter(this, R.layout.item_list, modelArrayList, sqLiteDatabase);
        recyclerView.setAdapter(itemAdapter);
    }

    private void findid() {
        recyclerView = findViewById(R.id.rv);
    }
}