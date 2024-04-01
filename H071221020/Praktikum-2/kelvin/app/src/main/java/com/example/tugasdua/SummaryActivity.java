package com.example.tugasdua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    ImageView image;
    TextView namet;
    TextView userNamet;
    TextView titlet;
    TextView notet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        MyParcelableData data = getIntent().getParcelableExtra("data");
        Uri imageUri = data.getImageUri();
        String name = data.getName();
        String userName = data.getUserName();
        String title = data.getTitle();
        String note = data.getNote();

        image = findViewById(R.id.imageSum);
        namet = findViewById(R.id.nameSum);
        userNamet = findViewById(R.id.userNameSum);
        titlet = findViewById(R.id.titleSum);
        notet = findViewById(R.id.noteSum);

        image.setImageURI(imageUri);
        namet.setText(name);
        userNamet.setText(userName);
        titlet.setText(title);
        notet.setText(note);


    }


}