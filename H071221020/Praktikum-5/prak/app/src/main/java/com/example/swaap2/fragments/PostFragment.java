package com.example.swaap2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swaap2.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swaap2.R;
import com.example.swaap2.models.Card;
import com.example.swaap2.models.Post;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PostFragment extends Fragment {

    private Uri imageUri;
    private String imageUrl;
    private String captionText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(getActivity());

        TextView caption = view.findViewById(R.id.caption_input);
        ImageView image = view.findViewById(R.id.image_input);
        Button post = view.findViewById(R.id.submit_post);

        captionText = caption.getText().toString();

        ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                result -> {
                    if (result != null) {
                        imageUri = result;
                        try {
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            image.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gambar tidak ditemukan", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        image.setOnClickListener(v -> galleryLauncher.launch("image/*"));

        post.setOnClickListener(v -> {
            String captionText = caption.getText().toString();
            if (!captionText.isEmpty()) {
                if (imageUri != null) {
                    post.setEnabled(false);
                    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("posts/" + imageUri.getLastPathSegment());
                    UploadTask uploadTask = storageRef.putFile(imageUri);
                    this.imageUrl = imageUri.getLastPathSegment();

                    Executor executor = Executors.newCachedThreadPool();
                    executor.execute(() -> {
                        try {
                            uploadTask.addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("items").child("posts");
                                    String postId = databaseRef.push().getKey();

                                    Post postData = new Post(postId, imageUrl, captionText, "1");
                                    databaseRef.child(postId).setValue(postData)
                                            .addOnSuccessListener(aVoid -> {
                                                Handler handler = new Handler(Looper.getMainLooper());
                                                handler.post(() -> {
                                                    if (getActivity() != null) {
                                                        post.setEnabled(true);
                                                        Toast.makeText(getActivity(), "Post berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                                                        FragmentManager fragmentManager = getParentFragmentManager();
                                                        HomeFragment homeFragment = new HomeFragment();
                                                        fragmentManager.beginTransaction()
                                                                .replace(R.id.fragmentContainerView, homeFragment)
                                                                .commit();
                                                    }
                                                });
                                            })
                                            .addOnFailureListener(e -> {
                                                Handler handler = new Handler(Looper.getMainLooper());
                                                handler.post(() -> {
                                                    if (getActivity() != null) {
                                                        post.setEnabled(true);
                                                        Toast.makeText(getActivity(), "Gagal menambahkan post", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            });
                                } else {
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    handler.post(() -> {
                                        if (getActivity() != null) {
                                            post.setEnabled(true);
                                            Toast.makeText(getActivity(), "Gagal mengunggah gambar", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(() -> {
                                if (getActivity() != null) {
                                    post.setEnabled(true);
                                    Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Masukkan caption terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}