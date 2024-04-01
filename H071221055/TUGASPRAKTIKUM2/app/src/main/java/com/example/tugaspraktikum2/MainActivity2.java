package com.example.tugaspraktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    Button buttonSubmit;
    EditText Title,Content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonSubmit = findViewById(R.id.Button2);
        Title = findViewById(R.id.Title);
        Content = findViewById(R.id.Content);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = Title.getText().toString();
                String content = Content.getText().toString();
                String nama = getIntent().getStringExtra("nama");
                String username = getIntent().getStringExtra("username");
                String imageUri = getIntent().getStringExtra("image");

                if (!title.isEmpty() && !content.isEmpty()) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    intent.putExtra("nama",nama);
                    intent.putExtra("username", username);
                    intent.putExtra("image", imageUri);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "Harap isi Kedua Kolom", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
