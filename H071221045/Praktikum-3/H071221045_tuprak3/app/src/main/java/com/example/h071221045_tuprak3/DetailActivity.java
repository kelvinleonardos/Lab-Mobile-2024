package com.example.h071221045_tuprak3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView da_profile = findViewById(R.id.da_profile);
        ImageView da_post = findViewById(R.id.da_post);
        TextView da_username = findViewById(R.id.da_username);
        TextView da_desc = findViewById(R.id.da_desc);

        String username = getIntent().getStringExtra("send_username");
        String caption = getIntent().getStringExtra("send_caption");
        int profile = getIntent().getIntExtra("send_profile_image",0);
        int story = getIntent().getIntExtra("send_story_image",0);
        int post = getIntent().getIntExtra("send_post_image",0);
        int following = getIntent().getIntExtra("send_following",0);
        int followers = getIntent().getIntExtra("send_followers",0);

        da_profile.setImageResource(profile);
        da_username.setText(username);
        da_post.setImageResource(post);
        da_desc.setText(caption);

        da_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this ,profileActivity.class);
                intent.putExtra("send_username",username);
                intent.putExtra("send_post_image",post);
                intent.putExtra("send_caption",caption);
                intent.putExtra("send_followers",followers);
                intent.putExtra("send_following",following);
                intent.putExtra("send_profile_image",profile);
                intent.putExtra("send_story_image",story);
                startActivity(intent);
            }
        });


    }
    }