package com.example.coffeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.views.NewsFragment;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<News> news;
    private final int[] images = {R.drawable.ic_news_photo};

    public NewsAdapter(List<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsAdapter.NewsViewHolder(newsItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.imageNews.setImageResource(images[position]);

        holder.textNews.setText(news.get(position).getText());
        holder.dateNews.setText(news.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static final class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageNews;
        TextView textNews;
        TextView dateNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageNews = itemView.findViewById(R.id.imageNews);
            textNews = itemView.findViewById(R.id.textNews);
            dateNews = itemView.findViewById(R.id.dateNews);
        }

    }
}