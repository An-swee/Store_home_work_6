package com.example.steven.hw6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by steven on 2017/12/7.
 */

public class MyDBHelper extends SQLiteOpenHelper{

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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String drop = "DROP TABLE main.exp";
        db.execSQL(drop);
        onCreate(db);
    }
}
