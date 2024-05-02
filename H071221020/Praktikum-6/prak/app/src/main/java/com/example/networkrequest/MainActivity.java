package com.example.networkrequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.networkrequest.ApiService;
import com.example.networkrequest.UserAdapter;
import com.example.networkrequest.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Button loadMoreButton;
    private Button refreshButton;
    private View progressBar;

    private List<User> userList = new ArrayList<>();
    private int currentPage = 1;
    private boolean isLoading = false;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.recyclerView);
        loadMoreButton = findViewById(R.id.loadMoreButton);
        refreshButton = findViewById(R.id.refreshButton);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);

        loadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoreUsers();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        loadData();
    }

    private void loadData() {
        if (!Utils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
            refreshButton.setVisibility(View.VISIBLE);
            loadMoreButton.setVisibility(View.GONE);
            return;
        }

        loadMoreButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<UserResponse> call = apiService.getUsers(currentPage);
                try {
                    Response<UserResponse> response = call.execute();
                    if (response.isSuccessful()) {
                        final List<User> users = response.body().getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userList.addAll(users);
                                userAdapter.notifyDataSetChanged();
                                currentPage++;
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                        }
                    });
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            loadMoreButton.setVisibility(View.VISIBLE);
                            refreshButton.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }

    private void loadMoreUsers() {
        if (isLoading) {
            return;
        }

        isLoading = true;

        loadMoreButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Call<UserResponse> call = apiService.getUsers(currentPage);
                try {
                    Response<UserResponse> response = call.execute();
                    if (response.isSuccessful()) {
                        final List<User> users = response.body().getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userList.addAll(users);
                                userAdapter.notifyDataSetChanged();
                                currentPage++;
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                        }
                    });
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isLoading = false;
                            loadMoreButton.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            refreshButton.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }

    private void refreshData() {
        userList.clear();
        userAdapter.notifyDataSetChanged();
        currentPage = 1;
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
