package com.example.h071221045_tuprak2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra("imageUri");
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        Uri imageUri = Uri.parse(imageUriString);

        ImageView imageData = findViewById(R.id.imageData);
        imageData.setImageURI(imageUri);

        TextView nameData = findViewById(R.id.nameData);
        nameData.setText(name);

        TextView usernameData = findViewById(R.id.usernameData);
        usernameData.setText(username);

        TextView titleData = findViewById(R.id.titleData);
        titleData.setText(title);

        TextView contentData = findViewById(R.id.contentData);
        contentData.setText(content);
    }
}
