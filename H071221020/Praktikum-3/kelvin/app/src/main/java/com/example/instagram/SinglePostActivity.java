package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagram.models.Owner;
import com.example.instagram.models.Post;
import com.squareup.picasso.Picasso;

public class SinglePostActivity extends AppCompatActivity {


    int userId;
    String caption, username, postUrl;
    String profile_pict;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Instagram");

        Intent intent = getIntent();
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        Cursor cursor = dbHelper.getPostById(intent.getIntExtra("postId", 0));


        if (cursor != null && cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_USER_ID));
                caption = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_CAPTION));
                postUrl = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_POST_URL));
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d("Post Data", "No posts found for user");
        }

        Log.d("waaa", caption);
        Log.d("waaa", postUrl);
        Cursor c = dbHelper.getUserById(userId);

        if (c != null && c.moveToFirst()) {
            do {
                profile_pict = c.getString(c.getColumnIndex(MyDatabaseHelper.COL_USER_PROFILE_PICTURE));
                username = c.getString(c.getColumnIndex(MyDatabaseHelper.COL_USERNAME));
            } while (cursor.moveToNext());
            c.close();
        } else {
            Log.d("Post Data", "No posts found for user");
        }


        ImageView profile = findViewById(R.id.single_post_profile_picture);
        TextView user = findViewById(R.id.single_post_username);
        TextView capt = findViewById(R.id.single_post_caption);
        ImageView post = findViewById(R.id.single_post_image);

        Picasso.get().load((profile_pict)).into(profile);
        user.setText(username);
        capt.setText(caption);
        Picasso.get().load(postUrl).into(post);

        TabBarListener.setButtonClickListener(this, findViewById(R.id.home_icon), findViewById(R.id.upload_icon), findViewById(R.id.profile_icon));
    }
}