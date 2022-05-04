package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coffeapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    MenuFragment menuFragment = new MenuFragment();
    NewsFragment newsFragment = new NewsFragment();
    MapFragment mapFragment = new MapFragment();
    PersonFragment personFragment = new PersonFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, new NewsFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_menu:
                    selectedFragment = menuFragment;
                    break;
                case R.id.nav_news:
                    selectedFragment = newsFragment;
                    break;
                case R.id.nav_map:
                    selectedFragment = mapFragment;
                    break;
                case R.id.nav_person:
                    selectedFragment = personFragment;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, selectedFragment).commit();
            return true;
        });
    }
}
