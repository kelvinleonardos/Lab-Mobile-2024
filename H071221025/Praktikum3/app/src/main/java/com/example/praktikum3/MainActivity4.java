package com.example.praktikum3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private ImageView iv_profile;
    private ImageView iv_feed;
    private TextView tv_nama, tv_caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();

        Main mains = intent.getParcelableExtra("main");


        iv_profile = findViewById(R.id.iv_profile4);
        iv_feed = findViewById(R.id.iv_feed3);
        tv_nama = findViewById(R.id.tv_namaprofile);
        tv_caption = findViewById(R.id.tv_caption2);


        iv_profile.setImageResource(mains.getImageprofile());
        iv_feed.setImageResource(mains.getImagefeed());
        tv_nama.setText(mains.getNama());
        tv_caption.setText(String.valueOf(mains.getCaption()));

        tv_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                intent.putExtra("main", mains);
                startActivity(intent);
            }
        });

        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                intent.putExtra("main", mains);
                startActivity(intent);
            }
        });
    }
}