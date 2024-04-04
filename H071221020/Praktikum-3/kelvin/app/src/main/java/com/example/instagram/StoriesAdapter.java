package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.models.Post;
import com.example.instagram.models.Story;
import com.squareup.picasso.Picasso;
import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesViewHolder> {
    private List<Story> stories;
    private Context context;

    public StoriesAdapter(List<Story> stories, Context context) {
        this.stories = stories;
        this.context = context;
    }

    @NonNull
    @Override
    public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_node, parent, false);
        return new StoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesViewHolder holder, int position) {
        final String username = stories.get(position).getUsername(context);
        final String profile_picture = stories.get(position).getProfile_picture(context);
        final int storyId = stories.get(position).getId();
        final int userId = stories.get(position).getUserId();
        final String storyUrl = stories.get(position).getStoryUrl();

        Story story = new Story(storyId, userId, storyUrl);

        holder.username.setText(username);
        Picasso.get().load(profile_picture).into(holder.feeds_content);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StoryActivity.class);
                i.putExtra("story", story);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}
