package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostinganAdapter extends RecyclerView.Adapter<PostinganAdapter.ViewHolder> {

    private ArrayList<Instagram> instagrams;

    public PostinganAdapter(ArrayList<Instagram> instagrams) {
        this.instagrams = instagrams;
    }

    @NonNull
    @Override    //mengubah xml ke view
    public PostinganAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postingan, parent, false);
        return new PostinganAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PostinganAdapter.ViewHolder holder, int position) {
        Instagram instagram = instagrams.get(position);

       // Menetapkan teks nama profil dari objek Instagram ke TextView tv_namaProfile
        holder.tv_namaProfile.setText(instagram.getNama());
        holder.ivProfile.setImageResource(instagram.getImageprofile());
        holder.ivFeed.setImageResource(instagram.getImagefeed());
        holder.tv_caption.setText(instagram.getCaption());

//        intent ketika ivProfile di klik
        holder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context, StoryActivity.class);
                intent.putExtra("instagram", instagram);
                holder.context.startActivity(intent);
            }
        });

        holder.tv_namaProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context, ProfileActivity.class);
                intent.putExtra("instagram", instagram);
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return instagrams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFeed, ivProfile;
        TextView tv_caption, tv_namaProfile;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFeed = itemView.findViewById(R.id.iv_post);
            ivProfile = itemView.findViewById(R.id.iv_profile);
            tv_namaProfile = itemView.findViewById(R.id.tv_user);
            tv_caption = itemView.findViewById(R.id.tv_desc);
            context = itemView.getContext();
        }
    }
}