package com.example.labp1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> dataList = new ArrayList<>(); // Daftar untuk menyimpan data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.listhasil);
        Button buttonSubmit = findViewById(R.id.button);
        LinearLayout listLayout = findViewById(R.id.listLayout);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = editText.getText().toString();

                dataList.add(inputText);
                listLayout.removeAllViews();

                for (String data : dataList) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(data);
                    textView.setTextColor(Color.BLACK);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
                    listLayout.addView(textView);

                    editText.setText("");
                }
            }
        });
    }
}