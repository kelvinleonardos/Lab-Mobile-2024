package com.example.praktikum_2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.gambar);
        EditText editText = findViewById(R.id.nama);
        EditText editText2 = findViewById(R.id.username);
        Button buttonSubmit = findViewById(R.id.btn_nama);

        //launcher untuk memulai aktivitas pemilihan gambar
        ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        image = data.getData();
                        imageView.setImageURI(image);
                    }
                }
        );

        imageView.setOnClickListener(view -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            launcherIntentGallery.launch(openGallery);
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String username = editText2.getText().toString();

                if (image != null) {
                    if (!name.isEmpty()) {
                        if (!username.isEmpty()) {
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            intent.putExtra("name", name);
                            intent.putExtra("username", username);
                            intent.putExtra("image", image.toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Username harus diisi", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Nama harus diisi", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Tambahkan foto terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}