package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private EditText editText3, editText4;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText3 = findViewById(R.id.eT3);
        editText4 = findViewById(R.id.eT4);
        button = findViewById(R.id.button);

        button.setOnClickListener(view ->{
            String text3 = editText3.getText().toString();
            String text4 = editText4.getText().toString();

            if (text3.isEmpty()) {
                editText3.setError("Field ini tidak boleh kosong");
            } else if (text4.isEmpty()) {
                editText4.setError("Field ini tidak boleh kosong");
            } else {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("title", text3);
                intent.putExtra("content", text4);


                String imageUri = getIntent().getStringExtra("imageUri");
                intent.putExtra("imageUri", imageUri);
                String text1 = getIntent().getStringExtra("nama");
                intent.putExtra("nama", text1);
                String text2 = getIntent().getStringExtra("username");
                intent.putExtra("username", text2);

                startActivity(intent);
            }
        });

    }
}
