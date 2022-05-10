package com.example.coffeapplication.mvvm.viewModels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.repositories.MenusRepository;

import java.util.ArrayList;

public class MenuStandartViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MenuItem>> currentName;
    MenusRepository menusRepository = new MenusRepository();

    public MutableLiveData<ArrayList<MenuItem>> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<MenuItem>>();
        }
        currentName.setValue(menusRepository.getStandartHolder());
        return currentName;
    }

    public ArrayList<MenuItem> getHolder() {
        return menusRepository.getStandartHolder();
    }
}
