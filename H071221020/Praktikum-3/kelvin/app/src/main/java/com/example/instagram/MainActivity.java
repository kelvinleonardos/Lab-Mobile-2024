package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.example.instagram.models.Owner;
import com.example.instagram.models.Post;
import com.example.instagram.models.Story;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper db;
    private RecyclerView storiesrecyclerView, postsrecyclerView;
    private StoriesAdapter storiesAdapter;
    private PostsAdapter postsAdapter ;
    private ArrayList<Story> stories;
    private ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Instagram");

        db = new MyDatabaseHelper(MainActivity.this);


        storiesrecyclerView = findViewById(R.id.story_node_section);
        storiesrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        postsrecyclerView = findViewById(R.id.post_section);
        postsrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        stories = new ArrayList<>();
        posts = new ArrayList<>();

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        Cursor cursorstory = dbHelper.getAllStories();

        if (cursorstory != null && cursorstory.moveToFirst()) {
            do {
                @SuppressLint("Range") int storyId = cursorstory.getInt(cursorstory.getColumnIndex(MyDatabaseHelper.COL_ID));
                @SuppressLint("Range") String userId = cursorstory.getString(cursorstory.getColumnIndex(MyDatabaseHelper.COL_USER_ID));
                @SuppressLint("Range") String storyUrl = cursorstory.getString(cursorstory.getColumnIndex(MyDatabaseHelper.COL_STORY_URL));

                Log.d("Story Data", "User ID: " + userId + ", Story URL: " + storyUrl);
                stories.add(new Story(storyId, Integer.parseInt(userId), storyUrl));
            } while (cursorstory.moveToNext());
            cursorstory.close();
        } else {
            Log.d("Story Data", "No story found");
        }
        storiesAdapter = new StoriesAdapter(stories, this);
        storiesrecyclerView.setAdapter(storiesAdapter);

        Cursor cursorpost = dbHelper.getAllPosts();

        if (cursorpost != null && cursorpost.moveToFirst()) {
            do {
                @SuppressLint("Range") int postId = cursorpost.getInt(cursorpost.getColumnIndex(MyDatabaseHelper.COL_ID));
                @SuppressLint("Range") int userId = cursorpost.getInt(cursorpost.getColumnIndex(MyDatabaseHelper.COL_USER_ID));
                @SuppressLint("Range") String caption = cursorpost.getString(cursorpost.getColumnIndex(MyDatabaseHelper.COL_CAPTION));
                @SuppressLint("Range") String postUrl = cursorpost.getString(cursorpost.getColumnIndex(MyDatabaseHelper.COL_POST_URL));

                posts.add(new Post(postId, userId, caption, postUrl));

                Log.d("Post Data", "Post ID: " + postId + ", User ID: " + userId + ", Caption: " + caption + ", Post URL: " + postUrl);
            } while (cursorpost.moveToNext());

            cursorpost.close();
        } else {
            Log.d("Post Data", "No posts found");
        }
        postsAdapter = new PostsAdapter(posts, this);
        postsrecyclerView.setAdapter(postsAdapter);

        TabBarListener.setButtonClickListener(this, findViewById(R.id.home_icon), findViewById(R.id.upload_icon), findViewById(R.id.profile_icon));

    }
}