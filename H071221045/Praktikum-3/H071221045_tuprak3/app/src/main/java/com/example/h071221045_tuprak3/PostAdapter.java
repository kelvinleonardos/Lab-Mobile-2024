package com.example.h071221045_tuprak3;

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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    public PostAdapter(ArrayList<Account> accounts, Context context) {
        this.accounts = accounts;
        this.context = context;
    }

    private final ArrayList<Account>accounts ;
    private final Context context;

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.post_item,parent,false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.setData(account);
        holder.post_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(context,profileActivity.class);
                    intent.putExtra("send_username",accounts.get(position).getUsername());
                    intent.putExtra("send_post_image",accounts.get(position).getPost_image());
                    intent.putExtra("send_caption",accounts.get(position).getCaption());
                    intent.putExtra("send_followers",accounts.get(position).getFollowers());
                    intent.putExtra("send_following",accounts.get(position).getFollowing());
                    intent.putExtra("send_profile_image",accounts.get(position).getImage_profile());
                    intent.putExtra("send_story_image",accounts.get(position).getStory_image());
                    context.startActivity(intent);
                }
            }
        });
        holder.post_image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(context,profileActivity.class);
                    intent.putExtra("send_username",accounts.get(position).getUsername());
                    intent.putExtra("send_post_image",accounts.get(position).getPost_image());
                    intent.putExtra("send_caption",accounts.get(position).getCaption());
                    intent.putExtra("send_followers",accounts.get(position).getFollowers());
                    intent.putExtra("send_following",accounts.get(position).getFollowing());
                    intent.putExtra("send_profile_image",accounts.get(position).getImage_profile());
                    intent.putExtra("send_story_image",accounts.get(position).getStory_image());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView post_image_profile;
        TextView post_username;
        ImageView image_post;
        TextView post_caption;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_image_profile= itemView.findViewById(R.id.po_profile);
            post_username = itemView.findViewById(R.id.po_username);
            image_post  = itemView.findViewById(R.id.po_post);
            post_caption  = itemView.findViewById(R.id.po_desc);
        }
        public void setData(Account account) {
            post_image_profile.setImageResource(account.getImage_profile());
            post_username.setText(account.getUsername());
            image_post.setImageResource(account.getPost_image());
            post_caption.setText(account.getCaption());
        }
    }
}