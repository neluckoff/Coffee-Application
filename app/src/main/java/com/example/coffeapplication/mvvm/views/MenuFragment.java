package com.example.coffeapplication.mvvm.views;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.adapters.TabAdapter;
import com.example.coffeapplication.mvvm.viewModels.MenuViewModel;
import com.google.android.material.tabs.TabLayout;

public class MenuFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MenuViewModel menuViewModel;
    private ImageView cartImage;
    private Dialog cartDialog;
    private CartFragment cartFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartFragment = new CartFragment();
    }

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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, cartFragment).commit();
            }
        });
    }
}
