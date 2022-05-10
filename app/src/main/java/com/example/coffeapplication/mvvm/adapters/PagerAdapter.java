package com.example.coffeapplication.mvvm.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.coffeapplication.mvvm.views.MenuBakeFragment;
import com.example.coffeapplication.mvvm.views.MenuFavoriteFragment;
import com.example.coffeapplication.mvvm.views.MenuFragment;
import com.example.coffeapplication.mvvm.views.MenuSeasonFragment;
import com.example.coffeapplication.mvvm.views.MenuStandartFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    private MenuFavoriteFragment menuFavoriteFragment;
    private MenuStandartFragment menuStandartFragment;
    private  MenuSeasonFragment menuSeasonFragment;
    private MenuBakeFragment menuBakeFragment;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
        menuBakeFragment = new MenuBakeFragment();
        menuFavoriteFragment = new MenuFavoriteFragment();
        menuSeasonFragment = new MenuSeasonFragment();
        menuStandartFragment = new MenuStandartFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return menuSeasonFragment;
            case 1:
                return menuStandartFragment;
            case 2:
                return menuBakeFragment;
            case 3:
                return menuFavoriteFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
