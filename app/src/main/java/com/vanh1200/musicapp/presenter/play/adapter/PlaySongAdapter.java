package com.vanh1200.musicapp.presenter.play.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PlaySongAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> arrFragment;

    public PlaySongAdapter(FragmentManager fm, ArrayList<Fragment> arrFragment) {
        super(fm);
        this.arrFragment = arrFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
    }


    @Override
    public int getCount() {
        return arrFragment.size();
    }
}
