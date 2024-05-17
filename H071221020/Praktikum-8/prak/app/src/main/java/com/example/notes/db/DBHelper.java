package com.example.notes.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notes.models.Note;
import com.example.notes.utils.Time;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "notes.db";
    final static int DB_VERSION = 1;
    final static String TABLE_NAME = "notes";
    final static String COL_ID = "id";
    final static String COL_TITLE = "title";
    final static String COL_CONTENT = "content";
    final static String COL_CREATED_AT = "created_at";
    final static String COL_UPDATED_AT = "updated_at";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_CONTENT + " TEXT, " +
                COL_CREATED_AT + " TEXT, " +
                COL_UPDATED_AT + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
            @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex(COL_CONTENT));
            @SuppressLint("Range") String createdAt = cursor.getString(cursor.getColumnIndex(COL_CREATED_AT));
            @SuppressLint("Range") String updatedAt = cursor.getString(cursor.getColumnIndex(COL_UPDATED_AT));
            notes.add(new Note(id, title, content, createdAt, updatedAt));
        }
        cursor.close();
        return notes;
    }

    public Note getNoteById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
        @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex(COL_CONTENT));
        @SuppressLint("Range") String createdAt = cursor.getString(cursor.getColumnIndex(COL_CREATED_AT));
        @SuppressLint("Range") String updatedAt = cursor.getString(cursor.getColumnIndex(COL_UPDATED_AT));
        cursor.close();
        return new Note(id, title, content, createdAt, updatedAt);
    }

    public void addNote(String title, String content) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_CONTENT, content);
        values.put(COL_CREATED_AT, Time.getCurrentDateTime());

        db.insert(TABLE_NAME, null, values);
    }

    public void updateNote(int id, String title, String content) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_CONTENT, content);
        values.put(COL_UPDATED_AT, Time.getCurrentDateTime());

        db.update(TABLE_NAME, values, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

}
