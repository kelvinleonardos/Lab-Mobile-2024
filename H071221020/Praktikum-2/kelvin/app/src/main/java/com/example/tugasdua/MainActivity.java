package com.example.tugasdua;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    Uri imageUri;
    EditText name;
    EditText userName;
    boolean changed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.unsignedImage);
        name = findViewById(R.id.nameInput);
        userName = findViewById(R.id.userNameInput);

        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            imageUri = data.getData();
                            imageView.setImageURI(imageUri);
                            changed = true;
                        }
                    }
                });

        imageView.setOnClickListener(
            view -> pickImageLauncher.launch(
                new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            )
        );

    }

    public void onAccountSubmit(View v) {
        if (changed) {
            Intent intent = new Intent(this, NoteActivity.class);
            MyParcelableData data = new MyParcelableData(imageUri, name.getText().toString(), userName.getText().toString(), "", "");
            intent.putExtra("data", data);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        }


    }

}