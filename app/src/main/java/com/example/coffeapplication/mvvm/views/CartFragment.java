package com.example.coffeapplication.mvvm.views;

import android.app.Application;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.adapters.CartAdapter;
import com.example.coffeapplication.mvvm.adapters.NewsAdapter;
import com.example.coffeapplication.mvvm.models.Cart;
import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.models.Orders;
import com.example.coffeapplication.mvvm.viewModels.CartViewModel;
import com.example.coffeapplication.mvvm.viewModels.NewsViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    CartAdapter adapter;
    CartViewModel cartViewModel;
    RecyclerView rcv;
    Button exit, pay;

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

        cartViewModel.getCurrentName(view).observe(getViewLifecycleOwner(), nameObserver);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),1);
        rcv.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exit = view.findViewById(R.id.cartClose);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, new MenuFragment()).commit();
            }
        });

        pay = view.findViewById(R.id.payBtn);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = mFirebaseDatabase.getReference("Cart");
                String id;

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id, name="", cost="";
                        int sum = 0;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Cart cart = ds.getValue(Cart.class);
                            name += cart.getName() + "\n";
                            cost += (cart.getCost() + "\n");
                            String a = (cart.getCost()
                                    .substring(0, cart.getCost().length()-1));
                            sum += (Integer.parseInt(a) * Integer.parseInt(cart.getCount()));
                        }
                        id = snapshot.getKey();
                        if (!name.isEmpty() && !cost.isEmpty()) {
                            Orders order = new Orders(id, sum+"p", name, cost);
                            Toast.makeText(getActivity().getApplication(), "Заказ оформлен", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, new MenuFragment()).commit();
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://coffe-application-default-rtdb.firebaseio.com/").getReference("Orders");
                            databaseReference.push().setValue(order);
                            DatabaseReference mData = FirebaseDatabase.getInstance("https://coffe-application-default-rtdb.firebaseio.com/").getReference("Cart");
                            mData.removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}