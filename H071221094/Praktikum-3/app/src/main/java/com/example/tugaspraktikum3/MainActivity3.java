package com.example.tugaspraktikum3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    public static final String ACT3 = "Parcelabel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        ImageView fotoAkun = findViewById(R.id.profilAccount);
        ImageView postinganAkun = findViewById(R.id.postinganAccount);
        TextView namaAkun = findViewById(R.id.namaAccount);
        TextView folower = findViewById(R.id.folower);
        TextView folowing = findViewById(R.id.folowing);

        Post akun = getIntent().getParcelableExtra(ACT3);
        fotoAkun.setImageResource(akun.getProfil());
        postinganAkun.setImageResource(akun.getFotopost());
        namaAkun.setText(akun.getNamaAkun());
        folower.setText(String.valueOf(akun.getFolower()));
        folowing.setText(String.valueOf(akun.getFolowing()));

        postinganAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                intent.putExtra(MainActivity4.ACT4,akun);
                startActivity(intent);
            }
        });

        fotoAkun.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                intent.putExtra(MainActivity2.ACT2,akun);
                startActivity(intent);
            }
        });

    }
}