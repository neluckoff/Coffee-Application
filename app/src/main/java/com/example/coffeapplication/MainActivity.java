package com.example.coffeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.coffeapplication.mvvm.viewModels.NewsViewModel;
import com.example.coffeapplication.mvvm.views.MapFragment;
import com.example.coffeapplication.mvvm.views.MenuFragment;
import com.example.coffeapplication.mvvm.views.NewsFragment;
import com.example.coffeapplication.mvvm.views.PersonFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}