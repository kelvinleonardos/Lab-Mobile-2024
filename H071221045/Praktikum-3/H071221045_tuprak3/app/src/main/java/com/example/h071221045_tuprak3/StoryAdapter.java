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

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    public StoryAdapter(ArrayList<Account> accounts, Context context) {
        this.accounts = accounts;
        this.context = context;
    }

    private final ArrayList<Account>accounts ;
    private final Context context;

    @NonNull
    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.story_item,parent,false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.ViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.setData(account);

        holder.sl_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(context,storyActivity.class);
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
        holder.sl_username.setOnClickListener(new View.OnClickListener() {
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
        ImageView sl_profile;
        TextView sl_username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sl_profile = itemView.findViewById(R.id.sl_profile);
            sl_username = itemView.findViewById(R.id.sl_username);

        }

        public void setData(Account account) {
            sl_profile.setImageResource(account.getImage_profile());
            sl_username.setText(account.getUsername());
        }
    }
}