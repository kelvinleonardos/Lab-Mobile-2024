package com.example.swaap2.models;

public class Post {
    String id;
    String imageUrl;
    String caption;
    String userId;

    public Post(String id, String imageUrl, String caption, String userId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public String getUserId() {
        return userId;
    }
}
