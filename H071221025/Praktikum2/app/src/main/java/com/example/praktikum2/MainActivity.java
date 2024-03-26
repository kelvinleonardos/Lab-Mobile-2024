package com.example.praktikum2;

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

    private Button button;
    private ImageView imageView;
    private EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.image1);
        editText1 = findViewById(R.id.eT1);
        editText2 = findViewById(R.id.eT2);

        imageView.setOnClickListener(view -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            launcherIntentGallery.launch(openGallery);
        });

        button.setOnClickListener(view -> {
            if (imageView.getTag() == null) {
                Toast.makeText(MainActivity.this, "Please pick a profile image first", Toast.LENGTH_SHORT).show();
            } else {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                if (text1.isEmpty()) {
                    editText1.setError("Field ini tidak boleh kosong");
                } else if (text2.isEmpty()) {
                    editText2.setError("Field ini tidak boleh kosong");
                } else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("nama", text1);
                    intent.putExtra("username", text2);

                    // Mendapatkan URI gambar dari ImageView
                    Uri imageUri = (Uri) imageView.getTag();
                    intent.putExtra("imageUri", imageUri.toString());

                    startActivity(intent);
                }
            }
        });
    }

    ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Uri imageUri = data.getData();
                    imageView.setImageURI(imageUri);
                    imageView.setTag(imageUri);
                }
            }
    );
}
