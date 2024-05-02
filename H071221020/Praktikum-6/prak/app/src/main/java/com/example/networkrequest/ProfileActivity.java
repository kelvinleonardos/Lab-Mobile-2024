package com.example.networkrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networkrequest.ApiService;
import com.example.networkrequest.RetrofitClient;
import com.example.networkrequest.UserResponse;
import com.example.networkrequest.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ImageView avatarImageView;
    LinearLayout userItemLayout;
    private ProgressBar progressBar;
    private TextView nameTextView;
    private TextView emailTextView;
    private Button refreshButton;
    private int userId;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        userId = intent.getIntExtra("id", 0);

        avatarImageView = findViewById(R.id.profile_page_image);
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        refreshButton = findViewById(R.id.refreshButton2);
        progressBar = findViewById(R.id.progressBar2);
        userItemLayout = findViewById(R.id.pro);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUserProfile(userId);
            }
        });

        loadUserProfile(userId);
    }

    private void loadUserProfile(int userId) {

        if (!Utils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
            refreshButton.setVisibility(View.VISIBLE);
            userItemLayout.setVisibility(View.GONE);
            return;
        } else {
            refreshButton.setVisibility(View.GONE);
        }

        progressBar.setVisibility(View.VISIBLE);
        userItemLayout.setVisibility(View.GONE);


        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<ResponseUser> call = apiService.getUser(userId);
                call.enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                        if (response.isSuccessful()) {
                            User user = response.body().getUser();
                            if (user != null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setVisibility(View.GONE);
                                        userItemLayout.setVisibility(View.VISIBLE);
                                        Picasso.get().load(user.getAvatar()).into(avatarImageView);
                                        nameTextView.setText(user.getName());
                                        emailTextView.setText(user.getEmail());
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUser> call, Throwable t) {

                    }
                });
            }
        });
    }
}
