package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.mvvm.adapters.NewsAdapter;
import com.example.coffeapplication.R;

import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.viewModels.NewsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    NewsAdapter adapter;
    RecyclerView recyclerView;
    NewsViewModel newsViewModel;
    RecyclerView rcv;
    BottomNavigationView bottomNavigationView;


    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        final Observer<ArrayList<News>> nameObserver = new Observer<ArrayList<News>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<News> plants) {
                adapter = new NewsAdapter(plants,requireContext());
                rcv.setAdapter(adapter);
            }
        };
        rcv = view.findViewById(R.id.newsRecycler);

        newsViewModel.getCurrentName().observe(getViewLifecycleOwner(), nameObserver);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(gridLayoutManager);

        return view;
    }


}