package com.example.tugas1praktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextUsername = findViewById(R.id.editTextUsername);
        editTextUsername.setTextColor(getResources().getColor(R.color.color_editText));
        Button buttonSave = findViewById(R.id.buttonSave);
        TextView textViewResult = findViewById(R.id.textViewResult);



        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();


                if (!TextUtils.isEmpty(username)){
                    String existingText = textViewResult.getText().toString();
                    if(!TextUtils.isEmpty(existingText)){
                        username += "\n";
                    }
                    String newText = username + existingText;
                    textViewResult.setText(newText);
                    textViewResult.setVisibility(textViewResult.VISIBLE);
                    editTextUsername.setText("");
                }
            }
        });
    }
}