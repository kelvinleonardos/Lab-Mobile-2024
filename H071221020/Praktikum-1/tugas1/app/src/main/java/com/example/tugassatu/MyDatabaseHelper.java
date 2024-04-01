package com.example.tugassatu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_NAME = "TaskList.db";
    public static final int DB_VERSION = 1;
    public static final String TB_NAME = "tasks";
    public static final String COL_ID = "_id";
    public static final String COL_TASK = "task";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TB_NAME +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TASK + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + TB_NAME);
    }

    public void addTask(Context context, String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TASK, task);
        long res = db.insert(TB_NAME, null, cv);
        if (res == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getTasks() {
        String query = "SELECT * FROM " + TB_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(query, null);
        }

        return c;
    }

    public void deleteTasks(Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TB_NAME, null, null);
        if (deletedRows > 0) {
            Toast.makeText(context, "Tasks Removed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "No Tasks Removed", Toast.LENGTH_SHORT).show();
        }
    }


}
