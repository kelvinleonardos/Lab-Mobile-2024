package com.example.swaap.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swaap.R;
import com.example.swaap.models.Card;
import com.example.swaap.recyclerviews.CardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    ArrayList<Card> cardItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardItemList = new ArrayList<>();

        TextView textNoPosts = view.findViewById(R.id.textNoPosts);

        RecyclerView recyclerView = view.findViewById(R.id.home_posts);

        DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("posts");

        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cardItemList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String postId = postSnapshot.getKey();

                    String imageUrl = postSnapshot.child("imageUrl").getValue(String.class);
                    String caption = postSnapshot.child("caption").getValue(String.class);
                    Card card = new Card(postId, imageUrl, caption);
                    cardItemList.add(card);
                }
                Collections.reverse(cardItemList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);

                if (cardItemList.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    textNoPosts.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    textNoPosts.setVisibility(View.GONE);

                    CardAdapter adapter = new CardAdapter(getActivity(), cardItemList);
                    recyclerView.setAdapter(adapter);
                };

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }
}