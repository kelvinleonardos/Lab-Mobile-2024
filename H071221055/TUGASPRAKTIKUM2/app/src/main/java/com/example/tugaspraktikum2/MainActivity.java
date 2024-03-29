package com.example.tugaspraktikum2;

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

    ImageView imageView;
    Uri image;
    EditText editText;
    EditText editText2;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.gallery);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        buttonSubmit = findViewById(R.id.button);

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
                String nama = editText.getText().toString();
                String username = editText2.getText().toString();

                if (!nama.isEmpty() && !username.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("nama", nama);
                    intent.putExtra("username", username);

                    if (image !=null){
                        intent.putExtra("image", image.toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Masukkan Gambar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Harap isi kedua kolom", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}