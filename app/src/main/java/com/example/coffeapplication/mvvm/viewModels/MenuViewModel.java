package com.example.coffeapplication.mvvm.viewModels;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.adapters.TabAdapter;
import com.example.coffeapplication.mvvm.views.MenuBakeFragment;
import com.example.coffeapplication.mvvm.views.MenuFavoriteFragment;
import com.example.coffeapplication.mvvm.views.MenuSeasonFragment;
import com.example.coffeapplication.mvvm.views.MenuStandartFragment;

public class MenuViewModel extends ViewModel {
    private final MenuSeasonFragment menuSeasonFragment;
    private final MenuBakeFragment menuBakeFragment;
    private final MenuStandartFragment menuStandartFragment;
    private final MenuFavoriteFragment menuFavoriteFragment;

    public MenuViewModel() {
        menuBakeFragment = new MenuBakeFragment();
        menuFavoriteFragment = new MenuFavoriteFragment();
        menuSeasonFragment = new MenuSeasonFragment();
        menuStandartFragment = new MenuStandartFragment();
    }

    public TabAdapter getTabAdapter(FragmentManager fragment) {
        TabAdapter tabAdapter = new TabAdapter(fragment, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabAdapter.addFragment(menuSeasonFragment, "Сезонное");
        tabAdapter.addFragment(menuStandartFragment, "Стандартное");
        tabAdapter.addFragment(menuBakeFragment, "Выпечка");
        tabAdapter.addFragment(menuFavoriteFragment, "Избранное");
        return tabAdapter;
    }
}
