package com.example.coffeapplication.mvvm.views;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.adapters.TabAdapter;
import com.example.coffeapplication.mvvm.viewModels.MenuViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MenuFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MenuViewModel menuViewModel;
    private ImageView cartImage;
    private Dialog cartDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        tabLayout.setupWithViewPager(viewPager);
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        TabAdapter tabAdapter = menuViewModel.getTabAdapter(getActivity().getSupportFragmentManager());

        viewPager.setAdapter(tabAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartDialog = new Dialog(getContext());

        cartImage = view.findViewById(R.id.imageView3);

        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartDialog.setContentView(R.layout.cart);
                cartDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cartDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                (cartDialog.findViewById(R.id.cartClose)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cartDialog.dismiss();
                    }
                });

                cartDialog.show();
            }
        });
    }
}
