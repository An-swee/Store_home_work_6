package com.example.steven.hw6;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        Log.d("del", "in ");
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        String delid = "_id = " + del_id.getText().toString();
        Cursor c = helper.getReadableDatabase().query(
                "exp", null, delid , null, null, null, null);
        Log.d("del", "search_store: ");
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.store_all,
                c,
                new String[]{"_id","store_name" , "phone" , "address"},
                new int[]{R.id.item_id , R.id.item_store_name , R.id.item_phone , R.id.item_address},
                0);
        Log.d("del", "show");
        list.setAdapter(adapter);
    }
    public void del(View v)
    {
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        String delid = "_id = " + del_id.getText().toString();
        helper.getWritableDatabase().delete("main.exp",delid,null);
        startActivity(
                new Intent(delActive.this,MainActivity.class));
    }
    public void notdel(View v)
    {
        startActivity(
                new Intent(delActive.this,MainActivity.class));
    }
}
