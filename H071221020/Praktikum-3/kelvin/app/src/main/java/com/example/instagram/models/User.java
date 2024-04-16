package com.example.instagram.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.instagram.MyDatabaseHelper;

public class User implements Parcelable {
    private int id;
    private String name;
    private String username;
    private String user_profile_picture;
    private String bio;

    // Constructor
    public User(int id, String name, String username, String user_profile_picture, String bio) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.user_profile_picture = user_profile_picture;
        this.bio = bio;
    }

    @SuppressLint("Range")
    public void dataSetter(Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        Cursor cursor = dbHelper.getUserById(id);

        Log.d("Owner", "Cursor count: " + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_NAME));
            bio = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_BIO));
            username = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USERNAME));
            user_profile_picture = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USER_PROFILE_PICTURE));

            cursor.close();
        } else {
            Log.d("Owner", "Cursor is null");
        }

        dbHelper.close();
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        user_profile_picture = in.readString();
        bio = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getUser_profile_picture() {
        return user_profile_picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(user_profile_picture);
        parcel.writeString(bio);
    }
}
