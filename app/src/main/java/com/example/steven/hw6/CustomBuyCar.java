package com.example.steven.hw6;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomBuyCar extends AppCompatActivity {

    Button amount_add;
    Button amount_down;
    Button buy;

    ImageView store_com_image;

    TextView com_all_money;
    TextView com_money;
    TextView store_c_name;
    TextView text_close;
    TextView buy_amount;
    private int int_buy_amount = 0;
    public static String com_name;
    public static int com_icon;
    public static int con_money;
    public static int store_com_all_amount;
    public static String com_store_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_buy_car);
        amount_add = (Button)findViewById(R.id.amount_up);
        amount_down = (Button)findViewById(R.id.amount_down);
        buy = (Button)findViewById(R.id.buy);

        store_com_image = (ImageView)findViewById(R.id.store_com_image);

        text_close = (TextView)findViewById(R.id.text_close);
        store_c_name  = (TextView)findViewById(R.id.store_com_name);
        com_money = (TextView)findViewById(R.id.com_money);
        com_all_money = (TextView)findViewById(R.id.com_all_money);
        buy_amount = (TextView)findViewById(R.id.buyamount);

        store_c_name.setText(com_store_name+"："+com_name);
        com_money.setText("商品金額："+con_money);
        com_all_money.setText("總計金額："+(int_buy_amount * con_money));
        store_com_image.setImageDrawable(getResources().getDrawable(com_icon));
        text_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        amount_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(int_buy_amount != store_com_all_amount)
                {
                    int_buy_amount = int_buy_amount + 1;
                    buy_amount.setText(Integer.toString(int_buy_amount));
                    com_all_money.setText("總計金額："+(int_buy_amount * con_money));
                }
                else
                    Toast.makeText(CustomBuyCar.this,"已達商品總數",Toast.LENGTH_SHORT).show();
            }
        });
        amount_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (int_buy_amount != 0) {
                    int_buy_amount = int_buy_amount - 1;
                    buy_amount.setText(Integer.toString(int_buy_amount));
                    com_all_money.setText("總計金額：" + (int_buy_amount * con_money));
                }
                else
                    Toast.makeText(CustomBuyCar.this,"已達最低下限",Toast.LENGTH_SHORT).show();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(int_buy_amount != 0) {
                    SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
                    Cursor c = db.rawQuery("SELECT * FROM "+com_store_name+" WHERE commodity = ?" , new String[]{com_name});
                    c.moveToFirst();
                    ContentValues values = new ContentValues();
                    values.put("amount", c.getInt(4)-int_buy_amount);
                    db.update(com_store_name,values,"commodity="+"'"+com_name+"'",null);
                    if(c.getInt(4)-int_buy_amount == 0){
                        String del_store_com = "commodity = " + com_name;
                        db.delete(com_store_name,del_store_com,null);
                    }
                    int_buy_amount = 0;
                    Toast.makeText(CustomBuyCar.this,"商品購買成功，歡迎下次光臨！",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(
                            new Intent(CustomBuyCar.this,moni_Buy_car.class));

                }
                else
                    Toast.makeText(CustomBuyCar.this,"購買數量為0！",Toast.LENGTH_SHORT).show();

            }
        });




    }
}
