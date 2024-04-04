package com.example.tugaspraktikum3;

import static com.example.tugaspraktikum3.DataSource.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_postingan;
    private RecyclerView rv_story;

    @SuppressLint("MissingInfflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        StoryAdapter storyAdapter = new StoryAdapter(posts);
        rv_story = findViewById(R.id.rv_story);
        rv_story.setAdapter(storyAdapter);
        rv_story.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.HORIZONTAL, false));
        rv_story.setHasFixedSize(true);
        rv_story.setAdapter(storyAdapter);


        PostAdapter postAdapter = new PostAdapter(posts);
        rv_postingan = findViewById(R.id.rv_postingan);
        rv_postingan.setAdapter(postAdapter);
        rv_postingan.setLayoutManager(new GridLayoutManager(this,1));
        rv_postingan.setHasFixedSize(true);
        rv_postingan.setAdapter(postAdapter);



    }
}