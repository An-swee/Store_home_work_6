package com.example.steven.hw6;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class order_management extends AppCompatActivity {
    public static String Search_name ;

    private SQLiteDatabase db;
    public long del_id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);
        final ListView list = (ListView) findViewById(R.id.list);
         db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
         Cursor c = db.rawQuery("SELECT * FROM " + Search_name+"order", null);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(order_management.this, MainActivity.class));
            }
        });
        SimpleCursorAdapter adapters = new SimpleCursorAdapter(this,
                R.layout.buy_car_show,
                c,
                new String[]{ "icon", "commodity", "money", "amount", "info"},
                new int[]{R.id.item3_icon, R.id.item3_com_name, R.id.item3_com_money, R.id.item3_com_amount, R.id.item3_com_info},
                0);
        list.setAdapter(adapters);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Cursor c = db.rawQuery("SELECT * FROM " + Search_name+"order WHERE _id = ?", new String[]{""+id});
                c.moveToFirst();
                Log.d("debug",c.getString(2));
                del_id = id ;
                final AlertDialog.Builder dialog = new AlertDialog.Builder(order_management.this);
                Log.d("debug",Search_name+"order"+",id:"+id+",get:"+c.getString(5));
                dialog.setTitle("出貨商品："+c.getString(2));
                dialog.setMessage("是否卻認出貨呢？");
                dialog.setNegativeButton("取消出貨",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(order_management.this, "已取消出貨！",Toast.LENGTH_SHORT).show();
                    }

                });
                dialog.setPositiveButton("確認出貨",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Cursor c = db.rawQuery("SELECT * FROM "+Search_name+"order"+" WHERE _id = ?" , new String[]{""+del_id});
                        c.moveToFirst();
                        String del_store_com = "_id = " + del_id;
                        db.delete(Search_name+"order",del_store_com,null);
                        Toast.makeText(order_management.this, "已出貨！",Toast.LENGTH_SHORT).show();

                        ContentValues vs = new ContentValues();
                        vs.put("icon", c.getString(1));
                        vs.put("commodity", c.getString(2));
                        vs.put("money", c.getString(3));
                        vs.put("amount", c.getString(4));
                        vs.put("info", c.getString(5));
                        vs.put("com_store_name" , c.getString(6));
                        db.insert(Search_name+"history", null, vs);

                        finish();
                        startActivity(
                                new Intent(order_management.this,order_management.class));
                    }

                });

                dialog.show();

            }
        });
    }
}