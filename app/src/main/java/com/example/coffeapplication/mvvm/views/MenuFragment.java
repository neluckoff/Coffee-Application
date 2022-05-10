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
import androidx.viewpager.widget.ViewPager;

import com.example.coffeapplication.R;
import com.example.coffeapplication.mvvm.adapters.PagerAdapter;
import com.example.coffeapplication.mvvm.viewModels.MenuViewModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MenuFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MenuViewModel menuViewModel;
    private ImageView cartImage, star;
    private Dialog cartDialog;
    private CartFragment cartFragment;
    private MenuFavoriteFragment menuFavoriteFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartFragment = new CartFragment();
        menuFavoriteFragment = new MenuFavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartDialog = new Dialog(getContext());

        cartImage = view.findViewById(R.id.imageView3);
        star = view.findViewById(R.id.imageViewStar);

        cartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, cartFragment).commit();
            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_news, menuFavoriteFragment).commit();
            }
        });
    }
}
