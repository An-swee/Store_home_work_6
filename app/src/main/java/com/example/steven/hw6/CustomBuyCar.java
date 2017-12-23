package com.example.steven.hw6;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomBuyCar extends Activity {

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
    public static int com_id;
    public static int com_icon;
    public static int con_money;
    public static int store_com_all_amount;
    public static String com_store_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

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
                    Cursor c = db.rawQuery("SELECT * FROM "+com_store_name+" WHERE _id = ?" , new String[]{""+com_id});
                    c.moveToFirst();
                    ContentValues values = new ContentValues();
                    Log.d("debug","com_name:"+c.getString(2)+",c_money:"+c.getInt(3)+",c_amount:"+c.getInt(4)+",c_new_amount"+(c.getInt(4)-int_buy_amount)+",serch_com_name:"+com_name);
                    values.put("amount", c.getInt(4)-int_buy_amount);
                    db.update(com_store_name,values,"_id="+"'"+c.getInt(0)+"'",null);
                    if(c.getInt(4)-int_buy_amount == 0){
                        String del_store_com = "_id = " + c.getInt(0);
                        db.delete(com_store_name,del_store_com,null);
                    }

                    Toast.makeText(CustomBuyCar.this,"商品購買成功，歡迎下次光臨！",Toast.LENGTH_SHORT).show();

                    ContentValues vs = new ContentValues();
                    vs.put("icon", c.getString(1));
                    vs.put("commodity", c.getString(2));
                    vs.put("money", ( int_buy_amount * con_money ) );
                    vs.put("amount", int_buy_amount);
                    vs.put("info", c.getString(5));
                    vs.put("com_store_name" , c.getString(6));
                    db.insert(com_store_name+"order", null, vs);
                    int_buy_amount = 0;
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
