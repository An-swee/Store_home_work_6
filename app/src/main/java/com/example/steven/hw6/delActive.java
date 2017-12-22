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
import android.widget.Toast;

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
        if (del_id.getText().toString().equals(""))
        {
            Toast.makeText(this,"未輸入查詢資料",Toast.LENGTH_SHORT).show();
        }
        else {

            //Cursor c = helper.getReadableDatabase().query(
            //        "exp", null, delstore_name , null, null, null, null);
            SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT * FROM main.exp WHERE store_name = ?", new String[]{del_id.getText().toString()});
            if(c.getCount() == 0)
                Toast.makeText(this,"查無此筆資料",Toast.LENGTH_LONG).show();
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.store_all,
                    c,
                    new String[]{"_id", "store_name", "phone", "address"},
                    new int[]{R.id.item_id, R.id.item_store_name, R.id.item_phone, R.id.item_address},
                    0);
            Log.d("search",""+c.getCount());
            list.setAdapter(adapter);
        }
    }
    public void del(View v)
    {

        SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM main.exp WHERE store_name = ?" , new String[]{del_id.getText().toString()});
        c.moveToFirst();
        String del_store_name = "store_name=" +"'" +del_id.getText().toString()+"'";
        Log.d("delsql",del_store_name);
        if(c.getCount() == 0)
            Toast.makeText(this,"查無此筆資料",Toast.LENGTH_LONG).show();
        else {
            db.delete("main.exp", del_store_name, null);
            db.execSQL("DROP TABLE IF EXISTS "+del_id.getText().toString());
            db.execSQL("DROP TABLE IF EXISTS "+del_id.getText().toString()+"order");
            db.execSQL("DROP TABLE IF EXISTS "+del_id.getText().toString()+"history");
            startActivity(
                    new Intent(delActive.this, MainActivity.class));
        }
    }
    public void notdel(View v)
    {
        startActivity(
                new Intent(delActive.this,MainActivity.class));
    }
}
