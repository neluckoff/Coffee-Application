package com.example.coffeapplication.mvvm.adapters;

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

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    ArrayList<News> data;
    Context context;

    public NewsAdapter(ArrayList<News> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {
        final News temp = data.get(position);

        holder.text.setText(data.get(position).getText());
        holder.date.setText(data.get(position).getDate());
        holder.img.setImageResource(data.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text, date;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageNewsItem);
            text=(TextView)itemView.findViewById(R.id.textNewsItem);
            date=(TextView)itemView.findViewById(R.id.dateNewsItem);
        }
    }

}

