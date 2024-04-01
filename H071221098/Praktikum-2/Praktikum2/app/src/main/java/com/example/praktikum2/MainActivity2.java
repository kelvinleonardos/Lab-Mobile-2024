package com.example.praktikum2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private EditText followers, following;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        done = findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String namee = getIntent().getStringExtra("keyname");
                String usernamee = getIntent().getStringExtra("keyusername");
                String image = getIntent().getStringExtra("keyimage");
                String followerss = followers.getText().toString();
                String followingg = following.getText().toString();

                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("keyname",namee);
                intent.putExtra("keyusername",usernamee);
                intent.putExtra("keyimage", image);
                intent.putExtra("keyfollowers",followerss);
                intent.putExtra("keyfollowing",followingg);
                startActivity(intent);
            }
        });

    }
}