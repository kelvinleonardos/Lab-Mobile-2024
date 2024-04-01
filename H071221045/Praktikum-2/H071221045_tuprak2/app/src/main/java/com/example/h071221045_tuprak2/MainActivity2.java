package com.example.h071221045_tuprak2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private EditText title;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String imageUri = getIntent().getStringExtra("imageUri");
        String name = getIntent().getStringExtra("name");
        String username = getIntent().getStringExtra("username");

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        Button button = findViewById(R.id.save_button);
        button.setOnClickListener(v -> {
            if (!title.getText().toString().isEmpty() && !content.getText().toString().isEmpty()) {
                goToMainActivity3(imageUri, name, username);
            } else {
                Toast.makeText(MainActivity2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMainActivity3(String imageUri, String name, String username) {

        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("imageUri", imageUri);
        intent.putExtra("name", name);
        intent.putExtra("username", username);
        intent.putExtra("title", title.getText().toString());
        intent.putExtra("content", content.getText().toString());
        startActivity(intent);
        finish();
    }
}
