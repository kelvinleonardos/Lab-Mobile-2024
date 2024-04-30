package com.example.swaap2.models;

public class Card {
    String postId;
    String userId;
    String username;
    String profileUrl;
    String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    String caption;

    public Card(String postId, String userId, String username, String profileUrl, String imageUrl, String caption, String name) {
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.name = name;
    }
}
