package com.example.instagram;


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
    public static final String DB_NAME = "instagram.db";
    public static final int DB_VERSION = 2;
    public static final String TB_NAME_USERS = "users";
    public static final String TB_NAME_POSTS = "posts";
    public static final String TB_NAME_STORIES = "stories";
    public static final String COL_ID = "id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_PROFILE_PICTURE = "user_profile_picture";
    public static final String COL_NAME = "name";
    public static final String COL_USERNAME = "username";
    public static final String COL_BIO = "bio";
    public static final String COL_POST_URL = "post_url";
    public static final String COL_STORY_URL = "story_url";
    public static final String COL_CAPTION = "caption";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUsers = "CREATE TABLE " + TB_NAME_USERS +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_USERNAME + " TEXT, " +
                COL_USER_PROFILE_PICTURE + " TEXT, " +
                COL_BIO + " TEXT);";

        String queryPosts = "CREATE TABLE " + TB_NAME_POSTS +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_ID + " INTEGER, " +
                COL_CAPTION + " TEXT, " +
                COL_POST_URL + " TEXT, " +
                "FOREIGN KEY (" + COL_USER_ID + ") REFERENCES " + TB_NAME_USERS + "(" + COL_ID + "));";

        String queryStories = "CREATE TABLE " + TB_NAME_STORIES +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_ID + " INTEGER, " +
                COL_STORY_URL + " TEXT, " +
                "FOREIGN KEY (" + COL_USER_ID + ") REFERENCES " + TB_NAME_USERS + "(" + COL_ID + "));";

        db.execSQL(queryUsers);
        db.execSQL(queryPosts);
        db.execSQL(queryStories);
        Log.d("kelvin", "onCreate: Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_POSTS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_STORIES);
        onCreate(db);
        Log.d("kelvin", "onUpgrade: Database upgraded");
    }
    https://picsum.photos/id/870/300/300
    public void addUser(Context context, String name, String username, String profile_picture, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_NAME, name);
        cv.put(COL_USERNAME, username);
        cv.put(COL_USER_PROFILE_PICTURE, profile_picture);
        cv.put(COL_BIO, bio);

        long result = db.insert(TB_NAME_USERS, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add user", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPost(Context context, int userId, String caption, String postUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_USER_ID, userId);
        cv.put(COL_CAPTION, caption);
        cv.put(COL_POST_URL, postUrl);

        long result = db.insert(TB_NAME_POSTS, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add post", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Post added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void addStory(Context context, int userId, String storyUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_USER_ID, userId);
        cv.put(COL_STORY_URL, storyUrl);

        long result = db.insert(TB_NAME_STORIES, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add story", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Story added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TB_NAME_USERS, null, null, null, null, null, null);
    }

    public Cursor getUserById(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_NAME, COL_USERNAME, COL_USER_PROFILE_PICTURE, COL_BIO};
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        return db.query(TB_NAME_USERS, columns, selection, selectionArgs, null, null, null);
    }

    public Cursor getAllPosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TB_NAME_POSTS, null, null, null, null, null, null);
    }

    public Cursor getAllStories() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(TB_NAME_STORIES, null, null, null, null, null, null);
    }

    public Cursor getPostById(int postId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_ID, COL_USER_ID, COL_CAPTION, COL_POST_URL};
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(postId)};
        return db.query(TB_NAME_POSTS, columns, selection, selectionArgs, null, null, null);
    }

    public Cursor getPostsByUserId(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_ID, COL_USER_ID, COL_CAPTION, COL_POST_URL};
        String selection = COL_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        return db.query(TB_NAME_POSTS, columns, selection, selectionArgs, null, null, null);
    }

    public Cursor getStoryById(int storyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_ID, COL_USER_ID, COL_STORY_URL};
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(storyId)};
        return db.query(TB_NAME_STORIES, columns, selection, selectionArgs, null, null, null);
    }

    public void deleteUserById(Context context, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        int deletedRows = db.delete(TB_NAME_USERS, selection, selectionArgs);
        if (deletedRows > 0) {
            Toast.makeText(context, "User deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStoryById(Context context, int storyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(storyId)};
        int deletedRows = db.delete(TB_NAME_STORIES, selection, selectionArgs);
        if (deletedRows > 0) {
            Toast.makeText(context, "Story deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete story", Toast.LENGTH_SHORT).show();
        }
    }


}
