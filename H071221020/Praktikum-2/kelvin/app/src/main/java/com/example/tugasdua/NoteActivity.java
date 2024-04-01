package com.example.tugasdua;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    EditText title;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        title = findViewById(R.id.titleInput);
        note = findViewById(R.id.noteInput);
    }


    public void onNoteSubmit(View v) {

        if (
            !title.getText().toString().isEmpty() &&
            !note.getText().toString().isEmpty()
        ) {
            MyParcelableData data = getIntent().getParcelableExtra("data");
            Uri imageUri = data.getImageUri();
            String name = data.getName();
            String userName = data.getUserName();

            Intent intent = new Intent(this, SummaryActivity.class);
            data = new MyParcelableData(imageUri, name, userName, title.getText().toString(), note.getText().toString());
            intent.putExtra("data", data);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please fill the fields", Toast.LENGTH_SHORT).show();
        }


    }
}