package com.example.tugas2praktikum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    EditText titleEditText,contentEditText;
   private String title;
   private String content;
    Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> saveDataAndNavigate());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void saveDataAndNavigate() {
        title = titleEditText.getText().toString();
        content = contentEditText.getText().toString();
        if ( !title.isEmpty() && !content.isEmpty()){
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("title", title);
            intent.putExtra("content", content);

            intent.putExtra("userInput", getIntent().getStringExtra("userInput"));
            intent.putExtra("usernameInput", getIntent().getStringExtra("usernameInput"));

            String imageUriString = getIntent().getStringExtra("imageUri");
            intent.putExtra("imageUri",imageUriString);

            startActivity(intent);
        }
        else {
            Toast.makeText(this,"lengkapi semua inputan", Toast.LENGTH_SHORT).show();
        }
    }
}