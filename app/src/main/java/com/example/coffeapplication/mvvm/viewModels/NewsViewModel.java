package com.example.coffeapplication.mvvm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.repositories.NewsRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<List<News>> newsList;

    private NewsRepository news;

    public void init(){
        if (newsList != null ){
            return ;
        }
        news = NewsRepository.getInstance();
        newsList = news.getNewsList();
    }

    public LiveData<List<News>> getNewsList(){
        return newsList;
    }
}

