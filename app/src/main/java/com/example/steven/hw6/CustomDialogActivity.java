package com.example.steven.hw6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialogActivity extends Activity {

    Button store_map;
    Button store_all;
    Button store_sell;
    Button store_car;

    TextView text_content;
    TextView store_name;
    TextView text_close;

    static String CDA_change_store_name;


    @Override    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CUD", "onCreate: ");
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_dialog);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        store_car = (Button)findViewById(R.id.store_car);
        store_map = (Button)findViewById(R.id.store_map);
        store_all = (Button)findViewById(R.id.store_all);
        store_sell = (Button)findViewById(R.id.store_sell);
        text_close = (TextView)findViewById(R.id.text_close);
        store_name  = (TextView)findViewById(R.id.store_name);
        text_content = (TextView)findViewById(R.id.text_content);

        store_name.setText(CDA_change_store_name);
        text_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        store_map.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                startActivity(
                        new Intent(CustomDialogActivity.this,MapsActivity.class));
                //Toast.makeText(CustomDialogActivity.this,"you click confirm!",Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
        store_all.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                startActivity(
                        new Intent(CustomDialogActivity.this,add_store_commodity.class));
                Toast.makeText(CustomDialogActivity.this,"進行"+CDA_change_store_name+"商品目錄管理", Toast.LENGTH_SHORT).show();
                //finish();
            }
        });




    }

}
