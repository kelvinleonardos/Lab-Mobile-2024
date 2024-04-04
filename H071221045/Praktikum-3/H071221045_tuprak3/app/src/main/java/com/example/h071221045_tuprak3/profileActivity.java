package com.example.h071221045_tuprak3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ImageView btn_back = findViewById(R.id.back_button);

        ImageView ava_profileimg = findViewById(R.id.ava_profileimg);
        TextView ava_username = findViewById(R.id.ava_username);
        TextView ava_followers = findViewById(R.id.ava_followers);
        TextView ava_following = findViewById(R.id.ava_following);
        ImageView ava_post = findViewById(R.id.ava_post);

        String username = getIntent().getStringExtra("send_username");
        String caption = getIntent().getStringExtra("send_caption");
        int profile = getIntent().getIntExtra("send_profile_image",0);
        int story = getIntent().getIntExtra("send_story_image",0);
        int post = getIntent().getIntExtra("send_post_image",0);
        int following = getIntent().getIntExtra("send_following",0);
        int followers = getIntent().getIntExtra("send_followers",0);

        ava_profileimg.setImageResource(profile);
        ava_username.setText(username);
        ava_followers.setText(String.valueOf(followers));
        ava_following.setText(String.valueOf(following));
        ava_post.setImageResource(post);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ava_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profileActivity.this ,DetailActivity.class);
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
        ava_profileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profileActivity.this ,storyActivity.class);
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