package com.example.coffeapplication.mvvm.viewModels;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.repositories.CartRepository;
import com.example.coffeapplication.mvvm.repositories.NewsRepository;
import com.example.coffeapplication.mvvm.views.CartFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Cart>> currentName;

    public MutableLiveData<ArrayList<Cart>> getCurrentName(View view) {
        CartRepository cartRepository = new CartRepository();

        if (currentName == null) {
            currentName = new MutableLiveData<ArrayList<Cart>>();
        }
        currentName.setValue(cartRepository.getFromDB(view));
        return currentName;
    }
}
