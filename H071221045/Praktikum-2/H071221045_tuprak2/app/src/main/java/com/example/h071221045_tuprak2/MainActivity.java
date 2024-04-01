package com.example.h071221045_tuprak2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView img;
    private Uri imgUri;
    private EditText name;
    private EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        name = findViewById(R.id.nama);
        user = findViewById(R.id.username);
        img = findViewById(R.id.profile);
        img.setOnClickListener(v -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            launcherIntentGallery.launch(openGallery);
        });

        btn.setOnClickListener(v -> {
            if (imgUri != null && !name.getText().toString().isEmpty() && !user.getText().toString().isEmpty()) {
                goToMainActivity2();
            } else {
                Toast.makeText(MainActivity.this, "Please fill in all fields and select an image before proceeding", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Uri image = data.getData();
                    img.setImageURI(image);
                    imgUri = image;
                } else {
                    Toast.makeText(MainActivity.this, "Please select an image", Toast.LENGTH_SHORT).show();
                }
            });

    private void goToMainActivity2() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("imageUri", imgUri.toString());
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("username", user.getText().toString());
        startActivity(intent);
    }
}