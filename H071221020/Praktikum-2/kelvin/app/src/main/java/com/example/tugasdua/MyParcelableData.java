package com.example.tugasdua;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MyParcelableData implements Parcelable {

    private Uri imageUri;
    private String name;
    private String userName;
    private String title;
    private String note;

    public MyParcelableData(Uri imageUri, String name, String userName, String title, String note) {
        this.imageUri = imageUri;
        this.name = name;
        this.userName = userName;
        this.title = title;
        this.note = note;
    }

    protected MyParcelableData(Parcel in) {
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        name = in.readString();
        userName = in.readString();
        title = in.readString();
        note = in.readString();
    }

    public static final Creator<MyParcelableData> CREATOR = new Creator<MyParcelableData>() {
        @Override
        public MyParcelableData createFromParcel(Parcel in) {
            return new MyParcelableData(in);
        }

        @Override
        public MyParcelableData[] newArray(int size) {
            return new MyParcelableData[size];
        }
    };

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(imageUri, i);
        parcel.writeString(name);
        parcel.writeString(userName);
        parcel.writeString(title);
        parcel.writeString(note);
    }
}
