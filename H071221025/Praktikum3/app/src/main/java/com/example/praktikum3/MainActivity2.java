package com.example.praktikum3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private ImageView iv_profile;
    private ImageView iv_story;
    private TextView tv_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        Main mains = intent.getParcelableExtra("main");

        iv_profile = findViewById(R.id.iv_profile2);
        iv_story = findViewById(R.id.iv_story);
        tv_nama = findViewById(R.id.tv_nama2);

        iv_profile.setImageResource(mains.getImageprofile());
        iv_story.setImageResource(mains.getImagestory());
        tv_nama.setText(mains.getNama());

        tv_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("main", mains);
                startActivity(intent);
            }
        });
    }
}