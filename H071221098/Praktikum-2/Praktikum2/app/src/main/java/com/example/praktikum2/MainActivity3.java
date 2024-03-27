package com.example.praktikum2;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    private TextView name, username, followers, following;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            username = findViewById(R.id.text_username);
            name = findViewById(R.id.text_name);
            followers = findViewById(R.id.text_followers);
            following = findViewById(R.id.text_following);



            String namee = getIntent().getStringExtra("keyname");
            String usernamee = getIntent().getStringExtra("keyusername");
            String followerss = getIntent().getStringExtra("keyfollowers");
            String followingg = getIntent().getStringExtra("keyfollowing");


            name.setText(namee);
            username.setText(usernamee);
            followers.setText(followerss);
            following.setText(followingg);

            ImageView imageView = findViewById(R.id.profile);
            String imageUriString = getIntent().getStringExtra("keyimage");

            if(imageUriString != null){
            Uri imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
            }

    }
}