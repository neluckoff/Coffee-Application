package com.example.coffeapplication.mvvm.viewModels;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.views.MenuBakeFragment;
import com.example.coffeapplication.mvvm.views.MenuFavoriteFragment;
import com.example.coffeapplication.mvvm.views.MenuSeasonFragment;
import com.example.coffeapplication.mvvm.views.MenuStandartFragment;

public class MenuViewModel extends ViewModel {

    public MenuViewModel() {
        MenuBakeFragment menuBakeFragment = new MenuBakeFragment();
        MenuFavoriteFragment menuFavoriteFragment = new MenuFavoriteFragment();
        MenuSeasonFragment menuSeasonFragment = new MenuSeasonFragment();
        MenuStandartFragment menuStandartFragment = new MenuStandartFragment();
    }
}
