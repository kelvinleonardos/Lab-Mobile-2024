package com.example.swaap2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.swaap2.fragments.HomeFragment;
import com.example.swaap2.fragments.PostFragment;
import com.example.swaap2.fragments.ProfileFragment;
import com.example.swaap2.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView homebutton = findViewById(R.id.home_button);
        ImageView searchbutton = findViewById(R.id.search_button);
        ImageView postbutton = findViewById(R.id.post_button);
        ImageView profilebutton = findViewById(R.id.profile_button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Swaap v2");

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainerView, homeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }

        homebutton.setOnClickListener(v -> {
            Fragment fragment1 = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
            if (!(fragment1 instanceof HomeFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, homeFragment, HomeFragment.class.getSimpleName())
                        .commit();
            }
        });

        searchbutton.setOnClickListener(v -> {
            Fragment postFragment = new SearchFragment();
            Fragment fragment2 = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());
            if (!(fragment2 instanceof SearchFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, postFragment, SearchFragment.class.getSimpleName())
                        .commit();
            }
        });

        postbutton.setOnClickListener(v -> {
            Fragment postFragment = new PostFragment();
            Fragment fragment3 = fragmentManager.findFragmentByTag(PostFragment.class.getSimpleName());
            if (!(fragment3 instanceof PostFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, postFragment, PostFragment.class.getSimpleName())
                        .commit();
            }
        });

        profilebutton.setOnClickListener(v -> {
            Fragment profileFragment = new ProfileFragment();
            Fragment fragment4 = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
            if (!(fragment4 instanceof ProfileFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, profileFragment, ProfileFragment.class.getSimpleName())
                        .commit();
            }
        });
    }
}