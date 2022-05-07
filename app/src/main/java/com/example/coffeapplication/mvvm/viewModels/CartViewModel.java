package com.example.coffeapplication.mvvm.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.repositories.CartRepository;
import com.example.coffeapplication.mvvm.repositories.NewsRepository;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Cart>> currentName;

    public MutableLiveData<ArrayList<Cart>> getCurrentName() {

        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<Cart>>();
        }
        currentName.setValue(new CartRepository().getHolder());
        return currentName;
    }
}
