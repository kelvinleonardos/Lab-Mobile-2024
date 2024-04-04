package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class TabBarListener {
    public static void setButtonClickListener(Context c, ImageButton button1, ImageButton button2, ImageButton button3) {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, MainActivity.class);
                c.startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, UploadActivity.class);
                c.startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, ProfileActivity.class);
                c.startActivity(intent);
            }
        });
    }

}
