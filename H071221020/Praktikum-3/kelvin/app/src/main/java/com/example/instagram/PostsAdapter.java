

package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.models.Post;
import com.example.instagram.models.Story;
import com.example.instagram.models.User;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsViewHolder> {
    private List<Post> posts;
    private Context context;
    Story story;

    public PostsAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_post, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        final String profilePicture = posts.get(position).getProfile_picture(context);
        final String username = posts.get(position).getUsername(context);
        final String caption = posts.get(position).getCaption();
        final String imageUrl = posts.get(position).getPostUrl();
        final int userId = posts.get(position).getUserId();
        final String name = posts.get(position).getName(context);
        final String bio = posts.get(position).getBio(context);
        final int postId = posts.get(position).getPostId();


        holder.username.setText(username);
        holder.caption.setText(caption);
        Picasso.get().load(profilePicture).into(holder.profile_pic);
        Picasso.get().load(imageUrl).into(holder.feeds_content);

        User user = new User(userId, name, username, profilePicture, bio);

        holder.profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.feeds_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SinglePostActivity.class);
                i.putExtra("postId", postId);
                context.startActivity(i);
            }
        });

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PublicUserProfileActivity.class);
                i.putExtra("data", user);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
