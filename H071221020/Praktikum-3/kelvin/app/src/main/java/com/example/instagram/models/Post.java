package com.example.instagram.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.instagram.MyDatabaseHelper;

public class Post {
    private int postId;
    private int userId;
    private String caption;
    private String postUrl;
    private String name, username, bio;
    private String profile_picture;

    // Constructor
    public Post(int postId, int userId, String caption, String postUrl) {
        this.postId = postId;
        this.userId = userId;
        this.caption = caption;
        this.postUrl = postUrl;
    }
    @SuppressLint("Range")
    public void dataSetter(Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        Cursor cursor = dbHelper.getUserById(userId);

        Log.d("Owner", "Cursor count: " + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_NAME));
            bio = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_BIO));
            username = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USERNAME));
            profile_picture = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USER_PROFILE_PICTURE));

            cursor.close();
        } else {
            Log.d("Owner", "Cursor is null");
        }

        dbHelper.close();
    }
    public int getPostId() {
        return postId;
    }
    public int getUserId() {
        return userId;
    }

    public String getCaption() {
        return caption;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public String getUsername(Context context) {
        dataSetter(context);
        return username;
    }

    public String getProfile_picture(Context context) {
        dataSetter(context);
        return profile_picture;
    }

    public String getName(Context context) {
        dataSetter(context);
        return name;
    }

    public String getBio(Context context) {
        dataSetter(context);
        return bio;
    }
}
