package com.example.instagram;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoriesViewHolder extends RecyclerView.ViewHolder {
    ImageView feeds_content;
    TextView username;

    public StoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        username = itemView.findViewById(R.id.story_node_username);
        feeds_content = itemView.findViewById(R.id.story_node);
    }

}