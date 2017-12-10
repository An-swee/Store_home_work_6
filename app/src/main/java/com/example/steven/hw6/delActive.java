package com.example.steven.hw6;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class delActive extends AppCompatActivity {
    private EditText del_id;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_active);
        del_id = (EditText) findViewById(R.id.del_id);
        list = (ListView) findViewById(R.id.list);
    }
    public void search_store(View v)
    {

        //Cursor c = helper.getReadableDatabase().query(
        //        "exp", null, delstore_name , null, null, null, null);
        SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM main.exp WHERE store_name = ?" , new String[]{del_id.getText().toString()});
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.store_all,
                c,
                new String[]{"_id","store_name" , "phone" , "address"},
                new int[]{ R.id.item_id,R.id.item_store_name , R.id.item_phone , R.id.item_address},
                0);
        list.setAdapter(adapter);
    }
    public void del(View v)
    {
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        String del_store_name = "store_name = " + del_id.getText().toString();
        helper.getWritableDatabase().delete("main.exp",del_store_name,null);
        startActivity(
                new Intent(delActive.this,MainActivity.class));
    }
    public void notdel(View v)
    {
        startActivity(
                new Intent(delActive.this,MainActivity.class));
    }
}
