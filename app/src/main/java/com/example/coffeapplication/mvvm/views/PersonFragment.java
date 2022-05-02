package com.example.coffeapplication.mvvm.views;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.coffeapplication.MainActivity;
import com.example.coffeapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PersonFragment extends Fragment {
    Button loyaltyProgram;
    Button exit;
    Button editProfile;
    Button historyOrders;
    Dialog loyaltyDialog;
    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loyaltyDialog = new Dialog(getContext());

        loyaltyProgram = view.findViewById(R.id.loyalityButton);

        loyaltyProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loyaltyDialog.setContentView(R.layout.loyalty_program);
                loyaltyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                loyaltyDialog.show();
            }
        });
    }

}
