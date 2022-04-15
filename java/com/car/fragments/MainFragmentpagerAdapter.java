package com.car.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainFragmentpagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragList;
    public MainFragmentpagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
