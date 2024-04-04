package com.example.h071221045_tuprak3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {
    public Account(String username, int story_image, int post_image, String caption, int followers, int following, int image_profile) {
        this.username = username;
        this.story_image = story_image;
        this.post_image = post_image;
        this.caption = caption;
        this.followers = followers;
        this.following = following;
        this.image_profile = image_profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStory_image() {
        return story_image;
    }

    public void setStory_image(int story_image) {
        this.story_image = story_image;
    }

    public int getPost_image() {
        return post_image;
    }

    public void setPost_image(int post_image) {
        this.post_image = post_image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(int image_profile) {
        this.image_profile = image_profile;
    }

    private String username, caption;
    private int story_image, post_image, followers, following, image_profile;

    protected Account(Parcel in) {
        username = in.readString();
        story_image = in.readInt();
        post_image = in.readInt();
        caption = in.readString();
        followers = in.readInt();
        following = in.readInt();
        image_profile = in.readInt();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(story_image);
        dest.writeInt(post_image);
        dest.writeString(caption);
        dest.writeInt(followers);
        dest.writeInt(following);
        dest.writeInt(image_profile);
    }
}