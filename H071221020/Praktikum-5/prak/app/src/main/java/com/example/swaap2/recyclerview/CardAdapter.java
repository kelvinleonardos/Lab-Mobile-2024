package com.example.swaap2.recyclerview;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.swaap2.ProfileActivity;
import com.example.swaap2.R;
import com.example.swaap2.models.Card;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<Card> cardItemList;
    private Context context;

    public CardAdapter(Context context, ArrayList<Card> cardItemList) {
        this.context = context;
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card cardItem = cardItemList.get(position);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRefpost = storage.getReference().child("posts");
        StorageReference storageRefprofile = storage.getReference().child("profiles");
        StorageReference imageRef = storageRefpost.child(cardItem.getImageUrl());
        StorageReference profileRef = storageRefprofile.child(cardItem.getProfileUrl());
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri.toString()).into(holder.imageView);;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {}
        });

        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri.toString()).into(holder.profile_picture);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {}
        });

        holder.captionTextView.setText(cardItem.getCaption());

        holder.profile_username.setText(cardItem.getUsername());

        holder.clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("username", cardItem.getUsername());
                intent.putExtra("name", cardItem.getName());
                intent.putExtra("imageUrl", cardItem.getImageUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView profile_picture;
        TextView captionTextView;
        TextView profile_username;

        LinearLayout clickable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clickable = itemView.findViewById(R.id.clickable);
            imageView = itemView.findViewById(R.id.post_image);
            captionTextView = itemView.findViewById(R.id.post_caption);
            profile_picture = itemView.findViewById(R.id.profile_picture);
            profile_username = itemView.findViewById(R.id.profile_username);
        }
    }
}