package com.example.tugas2praktikum;



import android.annotation.SuppressLint;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tugas2praktikum.MainActivity3;

public class MainActivity3 extends AppCompatActivity {
    private ImageView imageView3;
    private TextView textView3, textView4,contentTextView;
    private  TextView titleTextView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        String imageUriString = getIntent().getStringExtra("imageUri");
        String userInput = getIntent().getStringExtra("userInput");
        String usernameInput = getIntent().getStringExtra("usernameInput");
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");

        textView3 = findViewById(R.id.namaHasil);
        textView4 = findViewById(R.id.usernameHasil);
        titleTextView = findViewById(R.id.titleHasil);
        contentTextView = findViewById(R.id.contentHasil);
        imageView3 = findViewById(R.id.imageView3);


        textView3.setText(userInput);
        textView4.setText(usernameInput);

        titleTextView.setText(title);
        contentTextView.setText(content);

        if (imageUriString != null){
            Uri imageUri = Uri.parse(imageUriString);

            imageView3.setImageURI(imageUri);
        }



        }
    }
