package com.example.swaap2.models;

public class User {

    String id;
    String name;
    String username;
    String imageUrl;

    public User(String id, String name, String username, String imageUrl) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
