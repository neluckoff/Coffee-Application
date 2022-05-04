package com.example.coffeapplication.mvvm.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.repositories.MenuRepository;

import java.util.ArrayList;

public class MenuBakeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MenuItem>> currentName;

    public MutableLiveData<ArrayList<MenuItem>> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<MenuItem>>();
        }
        currentName.setValue(new MenuRepository().getBakeHolder());
        return currentName;
    }
}
