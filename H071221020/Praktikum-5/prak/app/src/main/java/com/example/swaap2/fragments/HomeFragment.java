package com.example.swaap2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swaap2.models.Card;
import com.example.swaap2.models.Post;
import com.example.swaap2.models.User;
import com.example.swaap2.recyclerview.CardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.example.swaap2.R;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {


    ArrayList<User> userItemList;
    ArrayList<Post> postItemList;
    ArrayList<Card> cards;

    private RecyclerView recyclerView;
    private TextView textNoPosts;
    private CardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postItemList = new ArrayList<>();
        userItemList = new ArrayList<>();
        cards = new ArrayList<>();

        textNoPosts = view.findViewById(R.id.textNoPosts);

        recyclerView = view.findViewById(R.id.home_posts);

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference().child("items");

        dataRef.child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot postSnapshot) {
                for (DataSnapshot dataPost : postSnapshot.getChildren()) {
                    String id = dataPost.getKey();
                    String imageUrl = dataPost.child("imageUrl").getValue(String.class);
                    String caption = dataPost.child("caption").getValue(String.class);
                    String userId = dataPost.child("userId").getValue(String.class);
                    Post post = new Post(id, imageUrl, caption, userId);
                    postItemList.add(post);
                    Query query = dataRef.child("users").orderByKey().equalTo(userId);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot userSnapshot) {
                            for (DataSnapshot dataUser : userSnapshot.getChildren()) {
                                String id = dataUser.getKey();
                                String name = dataUser.child("name").getValue(String.class);
                                String username = dataUser.child("username").getValue(String.class);
                                String imageUrl = dataUser.child("imageUrl").getValue(String.class);
                                User user = new User(id, name, username, imageUrl);
                                userItemList.add(user);
                            }
                            cards.clear();
                            for (Post post : postItemList) {
                                User user = userItemList.stream().filter(u -> u.getId().equals(post.getUserId())).findFirst().orElse(null);
                                if (user != null) {
                                    cards.add(new Card(post.getId(), user.getId(), user.getUsername(), user.getImageUrl(), post.getImageUrl(), post.getCaption(), user.getName()));
                                }
                            }
                            Collections.reverse(cards);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new CardAdapter(getActivity(), cards);
                            recyclerView.setAdapter(adapter);
                            if (cards.isEmpty()) {
                                recyclerView.setVisibility(View.GONE);
                                textNoPosts.setVisibility(View.VISIBLE);
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                textNoPosts.setVisibility(View.GONE);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }
}
