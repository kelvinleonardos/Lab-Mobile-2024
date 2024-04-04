package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.instagram.models.Owner;

public class UploadActivity extends AppCompatActivity {

    EditText inputURL, inputCaption;
    Button submitURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        inputURL = findViewById(R.id.post_url);
        inputCaption = findViewById(R.id.post_caption);
        submitURL = findViewById(R.id.submit_post_url);

        TabBarListener.setButtonClickListener(this, findViewById(R.id.home_icon), findViewById(R.id.upload_icon), findViewById(R.id.profile_icon));

        submitURL.setOnClickListener(v -> {
            MyDatabaseHelper db = new MyDatabaseHelper(UploadActivity.this);
            db.addPost(UploadActivity.this, Owner.getId(),inputCaption.getText().toString(), inputURL.getText().toString());
        });
    }
}