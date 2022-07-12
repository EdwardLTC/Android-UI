package com.edward.assigment.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.edward.assigment.Helper.TaskDataBase;

import java.util.ArrayList;

public class TaskDAO extends TaskDataBase {
    private static TaskDataBase taskDataBase;

    public TaskDAO(Context context){
        super(context);
        taskDataBase = new TaskDataBase(context);
    }

    public ArrayList<TaskToDo> getList(){
        ArrayList<TaskToDo> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = taskDataBase.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TaskDB",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add( new TaskToDo(cursor.getString(0)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insertTask(TaskToDo task){
        long value = -1;
        try {
            SQLiteDatabase sqLiteDatabase = taskDataBase.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("task", task.getTask());
            value = sqLiteDatabase.insert("TaskDB", null, contentValues);
        }catch (Exception ignored){
        }
        System.out.println(value);
        return value != -1;

    }


    public boolean deleteTask(String task){
        long value = -1;
        try {
            SQLiteDatabase sqLiteDatabase =  taskDataBase.getWritableDatabase();
            value= sqLiteDatabase.delete("TaskDB","Task = ?", new String[]{String.valueOf(task)});
        }
        catch (Exception ignored){
        }
        return value != -1;
    }

}
