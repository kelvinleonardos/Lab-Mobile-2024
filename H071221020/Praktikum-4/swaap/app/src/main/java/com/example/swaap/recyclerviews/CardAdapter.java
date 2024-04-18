package com.example.swaap.recyclerviews;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.swaap.ProfileActivity;
import com.example.swaap.R;
import com.example.swaap.fragments.ConfirmationDialogFragment;
import com.example.swaap.models.Card;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card cardItem = cardItemList.get(position);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("images");
        StorageReference imageRef = storageRef.child(cardItem.getImageUrl());
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri.toString()).into(holder.imageView);;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        holder.captionTextView.setText(cardItem.getCaption());

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                context.startActivity(intent);
            }
        });

        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment(cardItemList.get(holder.getAdapterPosition()), CardAdapter.this, holder.getAdapterPosition());
                dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "ConfirmationDialog");



//                Card cardItemToDelete = cardItemList.get(holder.getAdapterPosition());
//
//                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("posts").child(cardItemToDelete.getId());
//                databaseRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        FirebaseStorage storage = FirebaseStorage.getInstance();
//                        StorageReference storageRef = storage.getReference().child("images");
//                        StorageReference imageRef = storageRef.child(cardItemToDelete.getImageUrl());
//                        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                notifyItemRemoved(holder.getAdapterPosition());
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                            }
//                        });
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView captionTextView;
        ImageView delete_button;

        TextView profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_input);
            captionTextView = itemView.findViewById(R.id.post_caption);
            delete_button = itemView.findViewById(R.id.delete_button);
            profile = itemView.findViewById(R.id.home_profile);
        }
    }
}
