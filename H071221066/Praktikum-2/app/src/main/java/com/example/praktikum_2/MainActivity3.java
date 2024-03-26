package com.example.praktikum_2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView1 = findViewById(R.id.text1);
        textView1.setText(getIntent().getStringExtra("name"));

        TextView textView2 = findViewById(R.id.text2);
        textView2.setText(getIntent().getStringExtra("username"));

        TextView textView3 = findViewById(R.id.note1);
        textView3.setText(getIntent().getStringExtra("note1"));

        TextView textView4 = findViewById(R.id.note2);
        textView4.setText(getIntent().getStringExtra("note2"));

        ImageView imageView = findViewById(R.id.imageView);
        String imageUriString = getIntent().getStringExtra("image");

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
        }
    }
}