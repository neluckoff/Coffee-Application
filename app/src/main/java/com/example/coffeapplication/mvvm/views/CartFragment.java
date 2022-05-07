package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.adapters.CartAdapter;
import com.example.coffeapplication.mvvm.adapters.NewsAdapter;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.viewModels.CartViewModel;
import com.example.coffeapplication.mvvm.viewModels.NewsViewModel;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    CartAdapter adapter;
    CartViewModel cartViewModel;
    RecyclerView rcv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart, container, false);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        final Observer<ArrayList<Cart>> nameObserver = new Observer<ArrayList<Cart>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<Cart> cart) {
                adapter = new CartAdapter(cart,requireContext());
                rcv.setAdapter(adapter);
            }
        };
        rcv = view.findViewById(R.id.cartRec);

        cartViewModel.getCurrentName().observe(getViewLifecycleOwner(), nameObserver);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}