package com.example.coffeapplication.mvvm.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.coffeapplication.mvvm.models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    private static NewsRepository instance;
    private final List<News> newsList = new ArrayList<>();

    public static NewsRepository getInstance() {
        if (instance == null)
            instance = new NewsRepository();
        return instance;
    }

    public MutableLiveData<List<News>> getNewsList() {
        setNewsList();

        MutableLiveData<List<News>> data=new MutableLiveData<>();
        data.setValue(newsList);
        return data;
    }

    public void setNewsList() {
        newsList.add(new News(1, "ic_news_photo", "Новый латте", "21.03.2022"));
        newsList.add(new News(2, "ic_news_photo", "Новый капучино", "01.04.2022"));
        newsList.add(new News(3, "ic_news_photo", "Новый американо", "01.04.2022"));
        newsList.add(new News(4, "ic_news_photo", "Новый фрапучино", "02.04.2022"));
    }
}
