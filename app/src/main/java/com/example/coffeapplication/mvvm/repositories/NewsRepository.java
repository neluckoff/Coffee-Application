package com.example.coffeapplication.mvvm.repositories;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.News;

import java.util.ArrayList;

public class NewsRepository {
    ArrayList<News> holder;

    public NewsRepository() {
        holder = new ArrayList<>();

        News n1 = new News(R.drawable.ic_news_photo, "Новика", "21.02.2022");
        holder.add(n1);

        News n2 = new News(R.drawable.ic_news_photo, "Капучино грациас", "22.02.2022");
        holder.add(n2);

        News n3 = new News(R.drawable.ic_news_photo, "Латте по американски", "23.03.2022");
        holder.add(n3);

        News n4 = new News(R.drawable.ic_news_photo, "Опять новинка!", "21.04.2022");
        holder.add(n4);
    }

    public ArrayList<News> getHolder() {
        return holder;
    }
}
