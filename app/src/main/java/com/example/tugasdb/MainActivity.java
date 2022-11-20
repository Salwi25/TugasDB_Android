package com.example.tugasdb;

import static com.example.tugasdb.DBMain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBMain dbMain;
    SQLiteDatabase sqLiteDatabase;
    EditText itemName, itemPrice;
    Button save, view, edit;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbMain = new DBMain(this);
        // create method
        findid();
        insertData();
        editData();
    }

    private void editData() {
        if (getIntent().getBundleExtra("itemData")!=null){
            Bundle bundle = getIntent().getBundleExtra("itemData");
            id = bundle.getInt("id");
            itemName.setText(bundle.getString("itemName"));
            itemPrice.setText(bundle.getString("itemPrice"));
            edit.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);
        }
    }

    private void insertData() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("itemName", itemName.getText().toString());
                cv.put("itemPrice", itemPrice.getText().toString());

                sqLiteDatabase = dbMain.getWritableDatabase();
                Long recinsert = sqLiteDatabase.insert(TABLENAME, null, cv);
                if (recinsert != null){
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    //clear when click save
                    itemName.setText("");
                    itemPrice.setText("");
                }
            }
        });
        // when click on view button open view data activity
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewData.class);
                startActivity(intent);
            }
        });
        //storing edited data
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("itemName", itemName.getText().toString());
                cv.put("itemPrice", itemPrice.getText().toString());

                sqLiteDatabase = dbMain.getReadableDatabase();
                long recedit = sqLiteDatabase.update(TABLENAME, cv, "id="+id, null);
                if (recedit!=-1){
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                    save.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                }
            }
        });
    }

    private void findid() {
        itemName = (EditText) findViewById(R.id.item_name);
        itemPrice = (EditText) findViewById(R.id.item_price);
        save = (Button) findViewById(R.id.save_btn);
        view = (Button) findViewById(R.id.view_btn);
        edit = (Button) findViewById(R.id.edit_btn);
    }
}