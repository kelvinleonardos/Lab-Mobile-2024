package com.example.networkrequest;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networkrequest.models.User;
import com.squareup.picasso.Picasso;
import java.util.List;
public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public List<User> userList;
    public UserAdapter(List<User> userList){
        this.userList = userList;
    }
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent,
                        false);
        return new UserViewHolder(view);
    }
    public void onBindViewHolder(@NonNull UserViewHolder holder, int
            position) {
        User user = userList.get(position);
        holder.bind(user);
        holder.userItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),
                        ProfileActivity.class);
                intent.putExtra("id", user.getId());
                startActivity(v.getContext(), intent, null);
            }
        });
    }
    public int getItemCount() {
        return userList.size();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarImageView;
        private LinearLayout userItemLayout;
        private TextView nameTextView;
        private TextView emailTextView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userItemLayout = itemView.findViewById(R.id.userItemLinearLayout);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
        }
        public void bind(User user) {
            Picasso.get().load(user.getAvatar()).into(avatarImageView);
            nameTextView.setText(user.getFirst_name() + " " +
                    user.getLast_name());
            emailTextView.setText(user.getEmail());
        }
    }
}