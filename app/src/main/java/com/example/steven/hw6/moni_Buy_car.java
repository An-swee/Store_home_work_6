package com.example.steven.hw6;

import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class moni_Buy_car extends AppCompatActivity {

    private Cursor[] cursors = new Cursor[100];
    private int[] store_amount = new int[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moni__buy_car);
        Log.d("moni","gomoni");
        ListView list = (ListView) findViewById(R.id.list);
        SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
        Cursor c_store = db.rawQuery("SELECT * FROM main.exp ", null);

        Log.d("moni", ""+c_store.getCount());
        c_store.moveToFirst();
        final String store_name_all[];
        store_name_all = new String[100];
        for(int i = 0 ; i<= c_store.getCount()-1 ; i++) {
            store_name_all[i] = c_store.getString(1);
            c_store.moveToNext();

            cursors[i] = db.rawQuery("SELECT * FROM " + store_name_all[i] , null);
            store_amount[i] = cursors[i].getCount();
            Log.d("moni_buy_car,","Store_name:"+store_name_all[i]+",store_amount:"+store_amount[i]);
        }
        Cursor c = new MergeCursor(cursors);
        if (c.getCount() != 0) {
            SimpleCursorAdapter adapters = new SimpleCursorAdapter(this,
                    R.layout.buy_car_list,
                    c,
                    new String[]{"com_store_name", "icon", "commodity", "money", "amount", "info"},
                    new int[]{R.id.item3_com_store_name, R.id.item3_icon, R.id.item3_com_name, R.id.item3_com_money, R.id.item3_com_amount, R.id.item3_com_info},
                    0);
            list.setAdapter(adapters);
        }Log.d("moni","1");


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //openOptionsDialog(list.getItemAtPosition(position).toString());
                Log.d("position:",""+position);
                Log.d("parent:",""+parent);
                Log.d("view:",""+view);
                Log.d("id",""+id);

                int store_ck = 0;
                while ( ( store_amount[store_ck] - ( position + 1 ) )<0 )
                {
                    position = position - store_amount[store_ck] ;
                    store_ck++;
                }
                Log.d("List store:",store_name_all[store_ck]);
                //SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
                //Cursor ccc = db.rawQuery("SELECT * FROM " + store_name_all[store_ck] , null);


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
