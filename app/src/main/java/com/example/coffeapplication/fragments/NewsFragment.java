package com.example.coffeapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.adapter.NewsAdapter;
import com.example.coffeapplication.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    public static class NewsClass extends AppCompatActivity {
        RecyclerView newsRecycler;
        NewsAdapter newsAdapter;

        public NewsClass() {
            List<News> newsList = new ArrayList<>();
            newsList.add(new News(1, "ic_news_photo", "Новый латте", "21.03.2022"));
            newsList.add(new News(2, "ic_news_photo", "Новый капучино", "01.04.2022"));
            newsList.add(new News(3, "ic_news_photo", "Новый американо", "01.04.2022"));
            newsList.add(new News(4, "ic_news_photo", "Новый фрапучино", "02.04.2022"));

            setNewsRecycler(newsList);
        }

        private void setNewsRecycler(List<com.example.coffeapplication.model.News> newsList) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

            newsRecycler = findViewById(R.id.newsRecycler);
            newsRecycler.setLayoutManager(layoutManager);

            newsAdapter = new NewsAdapter(this, newsList);
            newsRecycler.setAdapter(newsAdapter);
        }
    }
}
