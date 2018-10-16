package com.vanh1200.musicapp.presenter.main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vanh1200.musicapp.view.fragment.mainFragment.AlbumFragment;
import com.vanh1200.musicapp.view.fragment.mainFragment.ArtistFragment;
import com.vanh1200.musicapp.view.fragment.mainFragment.SongFragment;

public class ViewpagerAdapter extends FragmentPagerAdapter{
    public static final int SIZE = 3;
    String [] arrTitle;

    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewpagerAdapter(FragmentManager fm, String[] arrTitle) {
        super(fm);
        this.arrTitle = arrTitle;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return SongFragment.getInstance();
            case 1:
                return AlbumFragment.getInstance();
            case 2:
                return ArtistFragment.getInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return SIZE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return arrTitle[0];
            case 1:
                return arrTitle[1];
            case 2:
                return arrTitle[2];
            default:
                return "Error";
        }
    }
}
