package com.example.instagram.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.instagram.MyDatabaseHelper;

public class Story implements Parcelable {
    private int id;
    private int userId;
    private String storyUrl;
    private String profile_picture;
    private String username;

    protected Story(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        storyUrl = in.readString();
        profile_picture = in.readString();
        username = in.readString();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    @SuppressLint("Range")
    public void dataSetter(Context context) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        Cursor cursor = dbHelper.getUserById(userId);

        Log.d("Owner", "Cursor count: " + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USERNAME));
            profile_picture = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_USER_PROFILE_PICTURE));
            cursor.close();
        } else {
            Log.d("Owner", "Cursor is null");
        }

        dbHelper.close();
    }

    // Constructor
    public Story(int id, int userId, String storyUrl) {
        this.id = id;
        this.userId = userId;
        this.storyUrl = storyUrl;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public String getUsername(Context context) {
        dataSetter(context);
        return username;
    }

    public String getProfile_picture(Context context) {
        dataSetter(context);
        return profile_picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(userId);
        parcel.writeString(storyUrl);
        parcel.writeString(profile_picture);
        parcel.writeString(username);
    }
}
