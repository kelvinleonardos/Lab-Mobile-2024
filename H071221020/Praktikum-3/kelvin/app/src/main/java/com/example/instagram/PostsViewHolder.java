package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsViewHolder extends RecyclerView.ViewHolder {
    ImageView feeds_content;
    TextView caption;
    TextView username;
    ImageView profile_pic;

    public PostsViewHolder(@NonNull View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.home_post_username);
        feeds_content = itemView.findViewById(R.id.home_post_image);
        caption = itemView.findViewById(R.id.home_post_caption);
        profile_pic = itemView.findViewById(R.id.story_profile_picture);


    }

}