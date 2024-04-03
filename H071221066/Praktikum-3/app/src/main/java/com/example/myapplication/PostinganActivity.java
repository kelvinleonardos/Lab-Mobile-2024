package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostinganActivity extends AppCompatActivity {

    private ImageView iv_profile;
    private ImageView iv_feed, iv_row;
    private TextView tv_nama, tv_caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postingan);

        Intent intent = getIntent();
        Instagram instagram = intent.getParcelableExtra("instagram");

        iv_profile = findViewById(R.id.iv_profile);
        iv_feed = findViewById(R.id.iv_post);
        tv_nama = findViewById(R.id.tv_user);
        tv_caption = findViewById(R.id.tv_desc);
        iv_row = findViewById(R.id.iv_row);

        iv_profile.setImageResource(instagram.getImageprofile());
        iv_feed.setImageResource(instagram.getImagefeed());
        tv_nama.setText(instagram.getNama());
        tv_caption.setText(String.valueOf(instagram.getCaption()));

        tv_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostinganActivity.this, ProfileActivity.class);
                intent.putExtra("instagram", instagram);
                startActivity(intent);
            }
        });

        iv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostinganActivity.this, StoryActivity.class);
                intent.putExtra("instagram", instagram);
                startActivity(intent);
            }
        });
    }
}