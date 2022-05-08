package com.example.coffeapplication.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.adapters.CartAdapter;
import com.example.coffeapplication.mvvm.adapters.OrderAdapter;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.Orders;
import com.example.coffeapplication.mvvm.viewModels.CartViewModel;
import com.example.coffeapplication.mvvm.viewModels.OrderViewModel;

import java.util.ArrayList;

public class OrderFragment extends Fragment {
    OrderAdapter adapter;
    OrderViewModel orderViewModel;
    RecyclerView rcv;
    Button exit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_history, container, false);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        final Observer<ArrayList<Orders>> nameObserver = new Observer<ArrayList<Orders>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<Orders> order) {
                adapter = new OrderAdapter(order,requireContext());
                rcv.setAdapter(adapter);
            }
        };
        rcv = view.findViewById(R.id.recyclerHistory);

        orderViewModel.getCurrentName(view).observe(getViewLifecycleOwner(), nameObserver);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exit = view.findViewById(R.id.closeHistoryBtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, new PersonFragment()).commit();
            }
        });
    }
}
