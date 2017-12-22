package com.example.steven.hw6;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class history_management extends AppCompatActivity {
    private SQLiteDatabase db;
    public static String Search_name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_management);
        final ListView list = (ListView) findViewById(R.id.list);
        db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM " + Search_name+"history", null);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(history_management.this, MainActivity.class));
            }
        });
        SimpleCursorAdapter adapters = new SimpleCursorAdapter(this,
                R.layout.sell_buy_car_show,
                c,
                new String[]{ "icon", "commodity", "money", "amount", "info"},
                new int[]{R.id.item3_icon, R.id.item3_com_name, R.id.item3_com_money, R.id.item3_com_amount, R.id.item3_com_info},
                0);
        list.setAdapter(adapters);
    }
}
