package com.example.steven.hw6;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addActivity extends AppCompatActivity {

    private EditText edstore_name;
    private EditText edphone;
    private EditText edaddress;
    private MyDBHelper helper;
    public static String cheek_map_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        helper = new MyDBHelper(this , "expense.db" , null , 1);
        findViews();
    }

    private void findViews()
    {
        edstore_name = (EditText) findViewById(R.id.edstore_name);
        edaddress = (EditText) findViewById(R.id.edaddress);
        edphone = (EditText) findViewById(R.id.edphone);
    }
    public void add(View v)
    {
        if (edstore_name.getText().toString().equals("") || edaddress.getText().toString().equals("") || edphone.getText().toString().equals(""))
        {
            Toast.makeText(this,"輸入資料不完全",Toast.LENGTH_SHORT).show();
        }
        else {

            String store_name = edstore_name.getText().toString();
            String address = edaddress.getText().toString();
            int phone = Integer.parseInt(edphone.getText().toString());
            ContentValues values = new ContentValues();

            values.put("store_name", store_name);
            values.put("address", address);
            values.put("phone", phone);

            long id = helper.getWritableDatabase().insert("exp", null, values);

            Log.d("ADD", id + "");
            helper.new_store_table(new String(""+store_name));
            helper.new_store_table(new String(""+store_name+"order"));
            helper.new_store_table(new String(""+store_name+"history"));
            startActivity(
                    new Intent(addActivity.this, MainActivity.class));
        }

    }
    public void notadd(View v)
    {
        startActivity(
                new Intent(addActivity.this,MainActivity.class));
    }

}
