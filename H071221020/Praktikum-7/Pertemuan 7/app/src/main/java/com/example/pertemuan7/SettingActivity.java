package com.example.pertemuan7;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {

    Button bt_tema;
    TextView tv_mode;
    Switch sw_tema;
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

//        bt_tema = findViewById(R.id.bt_tema);
        tv_mode = findViewById(R.id.tv_mode);
        sharedPreferences = getSharedPreferences("theme_pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        sw_tema = findViewById(R.id.sw_tema);

        boolean isDarkTheme = sharedPreferences.getBoolean("is_dark_theme", false);
        sw_tema.setChecked(isDarkTheme);
        sw_tema.setText(isDarkTheme ? "Dark Theme" : "Light Theme");

        sw_tema.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setDarkTheme();
            } else {
                setLightTheme();
            }
            editor.putBoolean("is_dark_theme", isChecked);
            editor.apply();
        });

//        bt_tema.setOnClickListener(v -> {
//            if (isDarkTheme) {
//                setLightTheme();
//            } else {
//                setDarkTheme();
//            }
//            editor.putBoolean("is_dark_theme", !isDarkTheme);
//            editor.apply();
//        });

    }

    private void setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}