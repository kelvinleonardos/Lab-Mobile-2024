package com.example.instagram.models;

import com.example.instagram.MyDatabaseHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class Owner {

    private static int id = 1;
    private static String name;
    private static String username;
    private static String profile_picture;
    private static String bio;

    @SuppressLint("Range")
    public static void dataSetter(Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        Cursor cursor = dbHelper.getUserById(id);

        Log.d("Owner", "Cursor count: " + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_NAME));
            username = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USERNAME));
            profile_picture = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USER_PROFILE_PICTURE));
            bio = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_BIO));

            cursor.close();
        } else {
            Log.d("Owner", "Cursor is null");
        }

        dbHelper.close();
    }
    // Getters
    public static int getId() {
        return id;
    }

    public static String getName(Context context) {
        dataSetter(context);
        return name;
    }

    public static String getUsername(Context context) {
        dataSetter(context);
        return username;
    }

    public static String getBio(Context context) {
        dataSetter(context);
        return bio;
    }

    public static String getProfile_picture(Context context) {
        dataSetter(context);
        return profile_picture;}

    public static void setProfile_picture(String profile_picture) {Owner.profile_picture = profile_picture;}
}
