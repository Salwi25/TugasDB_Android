package com.example.tugasdb;

import static com.example.tugasdb.DBMain.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ModelViewHolder> {
    Context context;
    ArrayList<Model>modelArrayList = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    //generate constructor

    public ItemAdapter(Context context, int item_list, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ItemAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ModelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Model model = modelArrayList.get(position);
        holder.txt_itemName.setText(model.getItemname());
        holder.txt_itemPrice.setText(model.getItemprice());

        // click edit button go to main activity
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", model.getId());
                bundle.putString("itemName", model.getItemname());
                bundle.putString("itemPrice", model.getItemprice());
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("itemData", bundle);
                context.startActivity(intent);
            }
        });
        //delete row
        holder.delete.setOnClickListener(new View.OnClickListener() {
            DBMain dbMain = new DBMain(context);
            @Override
            public void onClick(View view) {
                sqLiteDatabase = dbMain.getReadableDatabase();
                long delele = sqLiteDatabase.delete(TABLENAME, "id= "+model.getId(), null);
            if (delele!=-1){
                Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT).show();
                modelArrayList.remove(position);
                notifyDataSetChanged();
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView txt_itemName, txt_itemPrice;
        Button edit, delete;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_itemName = (TextView) itemView.findViewById(R.id.txt_itemName);
            txt_itemPrice = (TextView) itemView.findViewById(R.id.txt_itemPrice);
            edit = (Button) itemView.findViewById(R.id.txt_edit_btn);
            delete = (Button) itemView.findViewById(R.id.txt_delete_btn);
        }
    }
}
