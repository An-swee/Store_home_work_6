package com.example.steven.hw6;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialogActivity extends Activity {

    Button btn_confirm;
    Button btn_cancel;
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
        btn_confirm = (Button)findViewById(R.id.store_car);
        btn_cancel = (Button)findViewById(R.id.store_map);
        text_close = (TextView)findViewById(R.id.text_close);
        store_name  = (TextView)findViewById(R.id.store_name);
        text_content = (TextView)findViewById(R.id.text_content);

        store_name.setText(CDA_change_store_name);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                Toast.makeText(CustomDialogActivity.this,"you click confirm!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                Toast.makeText(CustomDialogActivity.this,"you click cancel!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        text_close.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                Toast.makeText(CustomDialogActivity.this,"you click close!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

}
