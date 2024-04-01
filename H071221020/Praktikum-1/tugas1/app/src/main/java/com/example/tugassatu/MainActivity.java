package com.example.tugassatu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper db;
    ArrayList<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDatabaseHelper(MainActivity.this);
        tasks = new ArrayList<>();

        displayTasks();

//        Log.d("kelvin", tasks.toString());

    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.greet);
        textView.setText("Selamat " + Utils.Time.getDayTime());
    }

    public void submitTask(View v) {
        EditText et = (EditText) findViewById(R.id.taskInput);
        String t = et.getText().toString();
//        MyDatabaseHelper db = new MyDatabaseHelper(MainActivity.this);
        db.addTask(MainActivity.this, t);
        displayTasks();
//        Log.d("kelvin", tasks.toString());
        et.setText("");
    }

    void displayTasks() {
        LinearLayout linearLayout = findViewById(R.id.tasksList);
        tasks.clear();
        Cursor c = db.getTasks();
        if (c.getCount() == 0) {
            Toast.makeText(this, "No Task.", Toast.LENGTH_SHORT).show();
        } else {
            while (c.moveToNext()) {
                tasks.add(c.getString(1));
            }
        }

        linearLayout.removeAllViews();

        for (String a : tasks) {
            TextView textView = new TextView(this);
            textView.setText(a);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            int paddingInDp = 5;
            float density = getResources().getDisplayMetrics().density;
            int paddingInPx = (int) (paddingInDp * density + 0.5f);

            textView.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
            linearLayout.addView(textView);
        }

    }

    public void clearTasks(View v) {
        db.deleteTasks(MainActivity.this);
        displayTasks();
    }


}