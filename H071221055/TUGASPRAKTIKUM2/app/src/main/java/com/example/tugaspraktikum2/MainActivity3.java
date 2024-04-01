package com.example.tugaspraktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView textView1,textView2,textView3,textView4;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView1 = findViewById(R.id.nama);
        textView2 = findViewById(R.id.username);
        textView3 = findViewById(R.id.Title);
        textView4 = findViewById(R.id.Content);
        image = findViewById(R.id.Image);

        textView1.setText(getIntent().getStringExtra("nama"));
        textView2.setText(getIntent().getStringExtra("username"));
        textView3.setText(getIntent().getStringExtra("title"));
        textView4.setText(getIntent().getStringExtra("content"));
        String imageUriString = getIntent().getStringExtra("image");
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            image.setImageURI(imageUri);
        }

    }
}