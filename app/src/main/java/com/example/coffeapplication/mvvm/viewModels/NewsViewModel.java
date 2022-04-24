package com.example.coffeapplication.mvvm.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.repositories.NewsRepository;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<News>> currentName;

    public MutableLiveData<ArrayList<News>> getCurrentName() {

        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<News>>();
        }
        currentName.setValue(new NewsRepository().getHolder());
        return currentName;
    }
}

