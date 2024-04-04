package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagram.R;
import com.example.instagram.models.Story;
import com.squareup.picasso.Picasso;

import java.time.Instant;

public class StoryActivity extends AppCompatActivity {

    ImageView storyImage, storyProfile;
    TextView storyUsername;
    String username, imageUrl, profileUrl;
    int storyId, userId;
    MyDatabaseHelper dbHelper;
    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        dbHelper = new MyDatabaseHelper(this);

        storyImage = findViewById(R.id.story_image);
        storyProfile = findViewById(R.id.story_profile_picture);
        storyUsername = findViewById(R.id.story_username);

        Intent intent = getIntent();
        story = intent.getParcelableExtra("story");

        username = story.getUsername(this);
        imageUrl = story.getStoryUrl();
        profileUrl = story.getProfile_picture(this);

        storyUsername.setText(username);
        Picasso.get().load(imageUrl).into(storyImage);
        Picasso.get().load(profileUrl).into(storyProfile);

    }
}