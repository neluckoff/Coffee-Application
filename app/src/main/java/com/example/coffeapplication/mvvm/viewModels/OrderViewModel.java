package com.example.coffeapplication.mvvm.viewModels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.Orders;
import com.example.coffeapplication.mvvm.repositories.CartRepository;
import com.example.coffeapplication.mvvm.repositories.OrderRepository;

import java.util.ArrayList;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Orders>> currentName;

    public MutableLiveData<ArrayList<Orders>> getCurrentName(View view) {
        OrderRepository orderRepository = new OrderRepository();

        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<Orders>>();
        }
        currentName.setValue(orderRepository.getFromDB(view));
        return currentName;
    }
}
