package com.edward.assigment.helper;

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
        String sql = "CREATE TABLE TaskDB (Task TEXT)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS  TaskDB";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
