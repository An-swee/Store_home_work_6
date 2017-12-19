package com.example.steven.hw6;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class add_store_commodity extends AppCompatActivity {
    public static String Search_name;
   // private SimpleAdapter simpleAdapter;
    private int[] image = { R.drawable.s00001, R.drawable.s00002, R.drawable.s00003,
            R.drawable.s00004, R.drawable.s00005, R.drawable.s00006,
            R.drawable.s00007, R.drawable.s00008, R.drawable.s00009,
            R.drawable.s00010, R.drawable.s00011, R.drawable.s00012};
    private Spinner spinner;
    private TextView store_com_name;
    private ListView list;
    private EditText ed_com_name,ed_com_money,ed_com_info,ed_com_amount;
    private String pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store_commodity);
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        helper.new_store_table(Search_name);


        store_com_name = (TextView) findViewById(R.id.store_com_name) ;
        spinner = (Spinner) findViewById(R.id.spinner);
        ed_com_amount = (EditText) findViewById(R.id.ed_com_amount);
        ed_com_info = (EditText) findViewById(R.id.ed_com_info);
        ed_com_money = (EditText) findViewById(R.id.ed_com_money);
        ed_com_name = (EditText) findViewById(R.id.ed_com_name);
        store_com_name.setText(Search_name+"商品目錄管理");
        list = (ListView) findViewById(R.id.list);



       // LayoutInflater inflater = LayoutInflater.from(this);
       // View layout1, layout2;
       // layout2 = inflater.inflate(R.layout.com_all, null);
       // setContentView(layout2);
        Log.d("add_com:",""+image);
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", image[i]);
            items.add(item);
            Log.d("add_com_icon:",""+image[i]);

        }Log.d("add_com_icon:",""+image);
       SimpleAdapter adapter =  new SimpleAdapter(this,
                items, R.layout.icon,
                new String[]{"image"},
                new int[]{R.id.imageView});
        spinner.setAdapter(adapter);

        SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM "+Search_name,null);

        if (c.getCount()!=0) {
            SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this,
                    R.layout.com_all,
                    c,
                    new String[]{"icon", "commodity", "money", "amount", "info"},
                    new int[]{R.id.item2_icon, R.id.item2_com_name, R.id.item2_com_money, R.id.item2_com_amount, R.id.item2_com_info},
                    0);

            list.setAdapter(adapter1);
        }
    }
    public void addtoadd(View v){
        if (ed_com_name.getText().toString().equals("") || ed_com_money.getText().toString().equals("") || ed_com_amount.getText().toString().equals("")) {
            Toast.makeText(this, "輸入資料不完全", Toast.LENGTH_SHORT).show();
        }
        else {
            if (Integer.parseInt(ed_com_amount.getText().toString()) == 0)
            {
                Toast.makeText(this, "商品數量不足無法上架", Toast.LENGTH_SHORT).show();
            }
            else {

                int com_icon;
                com_icon = 2131230855 + spinner.getSelectedItemPosition() ;

                //String com_icon =  pic + String.valueOf(spinner.getSelectedItemPosition()+1);

                Log.d("add_com", "" + com_icon);
                String com_name = ed_com_name.getText().toString();
                String com_money = ed_com_money.getText().toString();
                String com_amount = ed_com_amount.getText().toString();
                String com_info = ed_com_info.getText().toString();
                //Integer.parseInt(com_icon);
                //Log.d("add_comint", "" + com_icon);
                ContentValues values = new ContentValues();

                values.put("icon", com_icon);
                values.put("commodity", com_name);
                values.put("money", com_money);
                values.put("amount", com_amount);
                values.put("info", com_info);
                values.put("com_store_name" , Search_name);
                Log.d("add_com_spinner_post",""+com_icon);
                SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
                ;
                db.insert(Search_name, null, values);

                //Toast.makeText(this, "新增" + values + "商品", Toast.LENGTH_SHORT).show();

                ed_com_amount.setText("");
                ed_com_money.setText("");
                ed_com_name.setText("");
                ed_com_info.setText("");
            }

            SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT * FROM "+Search_name,null);
            //SQLiteDatabase dbb = helper.getWritableDatabase();

            Log.d("com","update");
            SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this,
                    R.layout.com_all,
                    c,
                    new String[]{"icon" , "commodity" , "money" , "amount" , "info"},
                    new int[]{R.id.item2_icon,R.id.item2_com_name , R.id.item2_com_money , R.id.item2_com_amount , R.id.item2_com_info},
                    0);

            list.setAdapter(adapter1);
        }

    }
    public void addtoend(View v) {
        if (ed_com_name.getText().toString().equals("") || ed_com_money.getText().toString().equals("") || ed_com_amount.getText().toString().equals("")) {
            Toast.makeText(this, "輸入資料不完全", Toast.LENGTH_SHORT).show();
        }
        else {
            if (Integer.parseInt(ed_com_amount.getText().toString()) == 0)
            {
                Toast.makeText(this, "商品數量不足無法上架", Toast.LENGTH_SHORT).show();
            }
            else {
                String com_icon = "" + spinner.getSelectedItemPosition();
                Log.d("add_com", "" + spinner.getSelectedItemPosition());
                String com_name = ed_com_name.getText().toString();
                String com_money = ed_com_money.getText().toString();
                String com_amount = ed_com_amount.getText().toString();
                String com_info = ed_com_info.getText().toString();

                ContentValues values = new ContentValues();

                values.put("icon", com_icon);
                values.put("commodity", com_name);
                values.put("money", com_money);
                values.put("amount", com_amount);
                values.put("info", com_info);

                SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
                ;
                db.insert(Search_name, null, values);

               // Toast.makeText(this, "新增" + values + "商品", Toast.LENGTH_SHORT).show();

                startActivity(
                        new Intent(this, MainActivity.class));
            }

        }
    }
    public void notadd(View v){
        Toast.makeText(this,"結束/放棄新增商品",Toast.LENGTH_LONG).show();
        startActivity(
                new Intent(this, MainActivity.class));
    }

}
