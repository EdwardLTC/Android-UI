package com.edward.assigment.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDataBase extends SQLiteOpenHelper {
    public static String DB_NAME = "TaskDB";
    public static int DB_VERSION = 1;

    public TaskDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TaskDB (Task TEXT,"+"user TEXT,"+"FOREIGN KEY (user) REFERENCES Account(user) ON DELETE CASCADE)");
        sqLiteDatabase.execSQL("CREATE TABLE Account (user TEXT  primary key,"+"password TEXT)");

        ContentValues contentValues = new ContentValues();
        contentValues.put("user", "admin" );
        contentValues.put("password","123");
        sqLiteDatabase.insert("Account",null,contentValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS  TaskDB");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  Account");
        onCreate(sqLiteDatabase);
    }
}
