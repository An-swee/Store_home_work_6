package com.example.steven.hw6;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private Button addAuc;
    private Button delAuc;
    private Button store_map_all;
    private ArrayAdapter<String> listAdapter ;

    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("main","啟動");
        addAuc = (Button) findViewById(R.id.addAuc);
        delAuc = (Button) findViewById(R.id.delAuc);

        final ListView list = (ListView) findViewById(R.id.list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(MainActivity.this, moni_Buy_car.class));
            }
        });

        addAuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(MainActivity.this,addActivity.class));
            }
        });
        delAuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(MainActivity.this,delActive.class));
            }
        });

        helper = new MyDBHelper(this, "expense.db", null, 1);
        helper.update();
        SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM main.exp ",null);
        //SQLiteDatabase dbb = helper.getWritableDatabase();

        //Log.d("main","update");
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.store_all,
                c,
                new String[]{"_id","store_name" , "phone" , "address"},
                new int[]{R.id.item_id , R.id.item_store_name , R.id.item_phone , R.id.item_address},
                0);
        //Log.d("maid","restart");
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //openOptionsDialog(list.getItemAtPosition(position).toString());
                Log.d("maid","dialog");

                SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
                Cursor c = db.rawQuery("SELECT * FROM main.exp WHERE _id = ?" , new String[]{Integer.toString(position+1)});
                c.moveToFirst();
                Log.d("maid",""+c.getString(1));
                add_store_commodity.Search_name = new String(c.getString(1));
                order_management.Search_name = new String(c.getString(1));
                history_management.Search_name = new String(c.getString(1));
                CustomDialogActivity.CDA_change_store_name = c.getString(1);
                MapsActivity.map_store_add = c.getString(2);
                startActivity(
                        new Intent(MainActivity.this,CustomDialogActivity.class));

            }




            //Intent intent = new Intent(MainActivity.this,dialog.class);
            //startActivity(intent);


            /*對話框所執行的 function
            private void openOptionsDialog(String xMessage) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("對話框的標題");
                // 承接傳過來的字串，顯示在對話框之中
                dialog.setMessage(xMessage);
                // 設定 PositiveButton 也就是一般 確定 或 OK 的按鈕
                dialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    public void onClick( DialogInterface dialoginterface, int i) {
                        // 當使用者按下確定鈕後所執行的動作
                    }
                } );
                dialog.setNeutralButton("歷史銷售紀錄", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                //設定 NegativeButton 也就是一般 取消 或 Cancel 的按鈕
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick( DialogInterface dialoginterface, int i) {
                        // 當使用者按下 取消鈕 後所執行的動作
                        openOptionsDialog("你要按確定鈕才能結束唷");
                    }
                });
                dialog.show();
            }*/


        });
    }
}
