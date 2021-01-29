package com.drew.tikitihub.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailAdapter extends FragmentPagerAdapter {

    private final List<Fragment> movieFragments = new ArrayList<>();
    private final List<String> movieFragmentTitles = new ArrayList<>();

    public MovieDetailAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return movieFragments.get(position);
    }

    @Override
    public int getCount() {
        return movieFragmentTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return movieFragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        movieFragments.add(fragment);
        movieFragmentTitles.add(title);
    }
}
