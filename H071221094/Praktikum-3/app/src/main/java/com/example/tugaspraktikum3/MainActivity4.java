package com.example.tugaspraktikum3;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity4 extends AppCompatActivity {

    public static final String ACT4 = "Parcelabel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        ImageView akunHasil = findViewById(R.id.profilHasil);
        ImageView postHasil = findViewById(R.id.postHasil);
        TextView deskripHasil = findViewById(R.id.deskriphasil);
        TextView namaHasil = findViewById(R.id.namaHasil);

        Post hasil = getIntent().getParcelableExtra(ACT4);
        akunHasil.setImageResource(hasil.getProfil());
        postHasil.setImageResource(hasil.getFotopost());
        deskripHasil.setText(hasil.getDeskrip());
        namaHasil.setText(hasil.getNamaAkun());


        namaHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this,MainActivity3.class);
                intent.putExtra(MainActivity3.ACT3,hasil);
                startActivity(intent);
            }
        });
    }
}