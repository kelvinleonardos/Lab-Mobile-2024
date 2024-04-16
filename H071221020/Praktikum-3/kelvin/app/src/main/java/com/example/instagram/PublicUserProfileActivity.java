package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagram.models.Owner;
import com.example.instagram.models.Post;
import com.example.instagram.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PublicUserProfileActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FeedsAdapter feedsAdapter;
    private ArrayList<Post> posts;
    ImageView profile_picture;
    TextView owner_name, owner_bio;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_user_profile);

        Intent i = getIntent();
        user = i.getParcelableExtra("data");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(user.getUsername());

        profile_picture = findViewById(R.id.public_profile_picture);
        owner_name = findViewById(R.id.public_name);
        owner_bio = findViewById(R.id.public_bio);

        Picasso.get().load(user.getUser_profile_picture()).into(profile_picture);
        owner_name.setText(user.getName());
        owner_bio.setText(user.getBio());

        recyclerView = findViewById(R.id.public_feeds_section);
        CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(this, 3, 1f);
        recyclerView.setLayoutManager(layoutManager);

        posts = new ArrayList<>();

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        int requserId = user.getId();
        Cursor cursor = dbHelper.getPostsByUserId(requserId);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int postId = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_ID));
                @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_USER_ID));
                @SuppressLint("Range") String caption = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_CAPTION));
                @SuppressLint("Range") String postUrl = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_POST_URL));

                Log.d("Post Data", "User ID: " + userId + ", Caption: " + caption + ", Post URL: " + postUrl);
                posts.add(new Post(postId, userId, caption, postUrl));
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d("Post Data", "No posts found for user with ID: " + requserId);
        }
        feedsAdapter = new FeedsAdapter(posts, this);
        recyclerView.setAdapter(feedsAdapter);
        Toast.makeText(this, "Total posts: " + posts.size(), Toast.LENGTH_SHORT).show();

        TabBarListener.setButtonClickListener(this, findViewById(R.id.home_icon), findViewById(R.id.upload_icon), findViewById(R.id.profile_icon));
    }
}