package com.example.coffeapplication.mvvm.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.NewsAdapter;
import com.example.coffeapplication.R;

import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.viewModels.NewsViewModel;

import java.util.List;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private NewsViewModel newsViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        TextView text = view.findViewById(R.id.textNews);
        TextView date = view.findViewById(R.id.dateNews);
        ImageView image = view.findViewById(R.id.imageNews);
        RecyclerView recyclerView = view.findViewById(R.id.newsRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        recyclerView = recyclerView.findViewById(R.id.newsRecycler);

        newsViewModel.init();

        newsViewModel.getNewsList().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


}