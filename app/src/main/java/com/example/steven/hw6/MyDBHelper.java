package com.example.steven.hw6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by steven on 2017/12/7.
 */

public class MyDBHelper extends SQLiteOpenHelper{



    static String del_store_table;


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //Log.d("SQL","sql in");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE main.exp " +
                "(_id INTEGER PRIMARY KEY  NOT NULL , " +
                "store_name VARCHAR NOT NULL , " +
                "address VARCHAR NOT NULL , " +
                "phone INTEGER NOT NULL)");
    }

    public boolean tabbleIsExist( String tableName){
        boolean result = false;
        SQLiteDatabase db = getWritableDatabase();
        if(tableName == null){
            return false;
        }
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='"+tableName.trim()+"' ";
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToNext()){
                int count = cursor.getInt(0);
                if(count>0){
                    result = true;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }


    public void new_store_table(String new_store_db_name)
    {
        if (tabbleIsExist(new_store_db_name) == false) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("CREATE  TABLE " + new_store_db_name +
                    "(_id INTEGER PRIMARY KEY  AUTOINCREMENT , " +
                    "icon INTEGER NOT NULL , " +
                    "commodity VARCHAR NOT NULL , " +
                    "money INTEGER NOT NULL , " +
                    "amount INTEGER NOT NULL , " +
                    "info VARCHAR , " +
                    "com_store_name VARCHAR )");
            Log.d("Table", "創建Table:" + new_store_db_name);
        }else
            Log.d("Table", "Table已經存在:" + new_store_db_name);
    }

    public void update()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM main.exp ",null);

        c.moveToFirst();
        for(int i=1;i<=c.getCount();i++) {
            if(i != c.getInt(0)) {
                ContentValues values = new ContentValues();
                values.put("_id",i);
                values.put("store_name", c.getString(1));
                values.put("address", c.getString(2));
                values.put("phone", c.getInt(3));
                db.insert("exp", null, values);
                String del_store_id = "_id = " + c.getString(0);
                db.delete("main.exp",del_store_id,null);
            }
            c.moveToNext();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String drop = "DROP TABLE IF EXISTS " + del_store_table;
        db.execSQL(drop);
        onCreate(db);
    }
}
