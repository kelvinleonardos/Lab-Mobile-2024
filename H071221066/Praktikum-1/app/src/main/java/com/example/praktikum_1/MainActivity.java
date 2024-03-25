package com.example.praktikum_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button btnoke;
    EditText editText;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnoke = (Button) findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.input);
        linearLayout = (LinearLayout) findViewById(R.id.linearlay);

        btnoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = editText.getText().toString();
                if (!newText.isEmpty()) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(newText);
                    textView.setTextSize(16);
                    textView.setTextColor(Color.parseColor("#8C6A5D"));
                    linearLayout.addView(textView);

                    // Menambahkan view sebagai garis pemisah
                    View separator = new View(MainActivity.this);
                    separator.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
                    separator.setBackgroundColor(Color.parseColor("#DED0B6"));
                    linearLayout.addView(separator);

                    editText.getText().clear();
                }
            }
        });
    }
}