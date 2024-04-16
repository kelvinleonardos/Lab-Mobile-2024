package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.models.Post;
import com.squareup.picasso.Picasso;
import java.util.List;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsViewHolder> {
    private List<Post> posts;
    private Context context;

    public FeedsAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeds, parent, false);
        return new FeedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsViewHolder holder, int position) {
        final String imageUrl = posts.get(position).getPostUrl();
        final int postId = posts.get(position).getPostId();

        Picasso.get().load(imageUrl).into(holder.feeds_content);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SinglePostActivity.class);
                i.putExtra("postId", postId);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
