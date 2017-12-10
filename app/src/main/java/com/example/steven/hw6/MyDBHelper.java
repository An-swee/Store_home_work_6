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

    //private MyDBHelper helper;
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE main.exp " +
                "(_id INTEGER PRIMARY KEY  NOT NULL , " +
                "store_name VARCHAR NOT NULL , " +
                "address VARCHAR NOT NULL , " +
                "phone INTEGER NOT NULL)");

    }

    public void update()
    {
        Log.d("DB","update-1");
        SQLiteDatabase db = getWritableDatabase();
        Log.d("DB","update0");
        Cursor c = db.rawQuery("SELECT * FROM main.exp ",null);
        Log.d("DB","update1");
        c.moveToFirst();
        Log.d("DB","update2");
        //Log.d("maid", "onCreate: "+String.valueOf(c.getCount()));
        for(int i=1;i<=c.getCount();i++) {

            if(i != c.getInt(0)) {

                ContentValues values = new ContentValues();
                values.put("_id",i);
                values.put("store_name", c.getString(1));
                values.put("address", c.getString(2));
                values.put("phone", c.getInt(3));
                db.insert("exp", null, values);
                Log.d("DB","update5");
                String del_store_id = "_id = " + c.getString(0);
                Log.d("DB","update6");
                db.delete("main.exp",del_store_id,null);
                Log.d("DB","update7");
            }
            c.moveToNext();Log.d("DB","update8");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String drop = "DROP TABLE main.exp";
        db.execSQL(drop);
        onCreate(db);
    }
}
