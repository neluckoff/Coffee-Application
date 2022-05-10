package com.example.coffeapplication.mvvm.viewModels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.MenuItem;

import com.example.coffeapplication.mvvm.repositories.MenuFavoriteRepository;
import com.example.coffeapplication.mvvm.repositories.MenusRepository;
import com.example.coffeapplication.mvvm.views.MenuFavoriteFragment;
import com.example.coffeapplication.mvvm.views.MenuFragment;

import java.util.ArrayList;

public class MenuFavoriteViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MenuItem>> currentName;

    public MutableLiveData<ArrayList<MenuItem>> getCurrentName(View view) {
        MenuFavoriteRepository menuFavoriteRepository = new MenuFavoriteRepository();

        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<MenuItem>>();
        }
        currentName.setValue(menuFavoriteRepository.getFavoriteFromDB(view));
        return currentName;
    }
}
