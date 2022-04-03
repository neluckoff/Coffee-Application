package com.example.coffeapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<News> news;
    Context context;

    public NewsAdapter(Context context, List<News> news) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newsItem = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NewsAdapter.NewsViewHolder(newsItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        int imageId = context.getResources().getIdentifier(news.get(position).getImage(), "drawable", context.getPackageName());
        holder.imageNews.setImageResource(imageId);

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
