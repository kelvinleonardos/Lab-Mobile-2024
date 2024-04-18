package com.example.swaap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.swaap.fragments.HomeFragment;
import com.example.swaap.fragments.PostFragment;
import com.example.swaap.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        LinearLayout homebutton = findViewById(R.id.home_button);
        LinearLayout postbutton = findViewById(R.id.post_button);
        LinearLayout profilebutton = findViewById(R.id.profile_button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Swaap");

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

        postbutton.setOnClickListener(v -> {
            Fragment postFragment = new PostFragment();
            Fragment fragment2 = fragmentManager.findFragmentByTag(PostFragment.class.getSimpleName());
            if (!(fragment2 instanceof PostFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, postFragment, PostFragment.class.getSimpleName())
                        .commit();
            }
        });

        profilebutton.setOnClickListener(v -> {
            Fragment profileFragment = new ProfileFragment();
            Fragment fragment3 = fragmentManager.findFragmentByTag(ProfileFragment.class.getSimpleName());
            if (!(fragment3 instanceof ProfileFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, profileFragment, ProfileFragment.class.getSimpleName())
                        .commit();
            }
        });

    }
}