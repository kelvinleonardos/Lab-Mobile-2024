package com.example.tugaspraktikum3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    public static final String ACT2 = "Parcelabel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);


        ImageView fotostory = findViewById(R.id.fotostory);
        ImageView profil_story = findViewById(R.id.profil_story);
        TextView text_story = findViewById(R.id.text_story);

        Post story = getIntent().getParcelableExtra(ACT2);
        fotostory.setImageResource(story.getStorypost());
        profil_story.setImageResource(story.getProfil());
        text_story.setText(story.getNamaAkun());


        text_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra(MainActivity3.ACT3,story);
                startActivity(intent);
            }
        });

    }
}