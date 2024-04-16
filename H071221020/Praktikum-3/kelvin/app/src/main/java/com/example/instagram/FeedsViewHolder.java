package com.example.instagram;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedsViewHolder extends RecyclerView.ViewHolder {
    ImageView feeds_content;

    public FeedsViewHolder(@NonNull View itemView) {
        super(itemView);
        feeds_content = itemView.findViewById(R.id.owner_feeds_content);
    }

}