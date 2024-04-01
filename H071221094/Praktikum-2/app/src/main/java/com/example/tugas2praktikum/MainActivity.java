package com.example.tugas2praktikum;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView2;
    private EditText editText2,editText3;
    private Button button;
    private String userInput,usernameInput;
    private Uri selectedimage;


    ActivityResultLauncher<Intent>launcherIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),result->{
                onActivityResult(result);
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView2 = findViewById(R.id.imageView2);
        button = findViewById(R.id.button);

        imageView2.setOnClickListener(view->{
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");

            launcherIntentGallery.launch(openGallery);

        });
        button.setOnClickListener(view->{
            saveAndNavigateToNextPage();
        });

    }

    private void saveAndNavigateToNextPage() {
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        userInput = editText2.getText().toString();
        usernameInput = editText3.getText().toString();

        if (!userInput.isEmpty()&&!usernameInput.isEmpty()&&selectedimage!=null){
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            intent.putExtra("userInput", userInput);
            intent.putExtra("usernameInput", usernameInput);
            intent.putExtra("imageUri", selectedimage.toString());
            startActivity(intent);
        }else {
            Toast.makeText(this,"Lengkapi semua inputan",Toast.LENGTH_SHORT).show();
        }
    }

    private void onActivityResult(ActivityResult result){
        if (result.getResultCode()== Activity.RESULT_OK){
            Intent data = result.getData();
            if (data != null){
                Uri image = data.getData();
                if (image != null){
                    imageView2.setImageURI(image);
                    selectedimage = image;
                }
            }
        }
    }
}