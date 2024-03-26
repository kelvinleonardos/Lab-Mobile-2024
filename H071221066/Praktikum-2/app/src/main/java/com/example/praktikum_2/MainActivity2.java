package com.example.praktikum_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        Button button = findViewById(R.id.btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image = getIntent().getStringExtra("image");
                String name = getIntent().getStringExtra("name");
                String username = getIntent().getStringExtra("username");

                String note = editText1.getText().toString();
                String note2 = editText2.getText().toString();

                if (!note.isEmpty() && !note2.isEmpty()) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("image", image);
                    intent.putExtra("name", name);
                    intent.putExtra("username", username);
                    intent.putExtra("note1", note);
                    intent.putExtra("note2", note2);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "Harap isi kedua kolom", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}