package com.example.coffeapplication.mvvm.viewModels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.repositories.MenusRepository;

import java.util.ArrayList;

public class MenuBakeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MenuItem>> currentName;

    public MutableLiveData<ArrayList<MenuItem>> getCurrentName() {
        MenusRepository menusRepository = new MenusRepository();
        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<MenuItem>>();
        }
        currentName.setValue(menusRepository.getBakeHolder());
        return currentName;
    }

}
