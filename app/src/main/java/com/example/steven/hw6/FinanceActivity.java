package com.example.steven.hw6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FinanceActivity extends AppCompatActivity {


    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_finance);
        ListView list = (ListView) findViewById(R.id.list);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(FinanceActivity.this, addActivity.class));
            }
        });

        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        Cursor c = helper.getReadableDatabase().query(
                "exp", null, null, null, null, null, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.store_all,
                c,
                new String[]{"store_name" , "phone" , "address"},
                new int[]{ R.id.item_store_name , R.id.item_phone , R.id.item_address},
                0);
        list.setAdapter(adapter);



    }
  /*
    public void onClick (View view)
    {
        startActivity(
                new Intent(FinanceActivity.this, addActivity.class));
    }
    */

}
