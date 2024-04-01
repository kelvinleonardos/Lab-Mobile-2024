package com.example.praktikum_1;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private EditText et;
    private Button btn;
    private String[] data = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);
        et = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> {
            String inputText = et.getText().toString();

            int newLength = data.length + 1;
            String[] newData = new String[newLength];
            System.arraycopy(data, 0, newData, 0, data.length);
            newData[newLength - 1] = inputText;
            data = newData;

            et.getText().clear();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_layout, data);
            lv.setAdapter(adapter);
        });
    }
}