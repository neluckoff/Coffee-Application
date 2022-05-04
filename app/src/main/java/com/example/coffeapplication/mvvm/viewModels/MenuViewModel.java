package com.example.coffeapplication.mvvm.viewModels;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coffeapplication.mvvm.adapters.TabAdapter;
import com.example.coffeapplication.mvvm.models.MenuItem;
import com.example.coffeapplication.mvvm.models.News;
import com.example.coffeapplication.mvvm.repositories.MenuRepository;
import com.example.coffeapplication.mvvm.repositories.NewsRepository;
import com.example.coffeapplication.mvvm.views.MenuBakeFragment;
import com.example.coffeapplication.mvvm.views.MenuFavoriteFragment;
import com.example.coffeapplication.mvvm.views.MenuFragment;
import com.example.coffeapplication.mvvm.views.MenuSeasonFragment;
import com.example.coffeapplication.mvvm.views.MenuStandartFragment;

import java.util.ArrayList;

public class MenuViewModel extends ViewModel {

    public TabAdapter getTabAdapter(FragmentManager fragment) {
        TabAdapter tabAdapter = new TabAdapter(fragment, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabAdapter.addFragment(new MenuSeasonFragment(), "Сезонное");
        tabAdapter.addFragment(new MenuStandartFragment(), "Стандартное");
        tabAdapter.addFragment(new MenuBakeFragment(), "Выпечка");
        tabAdapter.addFragment(new MenuFavoriteFragment(), "Избранное");
        return tabAdapter;
    }
}
