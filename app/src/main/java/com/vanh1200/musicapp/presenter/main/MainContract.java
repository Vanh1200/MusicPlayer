package com.vanh1200.musicapp.presenter.main;

public interface MainContract {
    interface View{
        void setViewPager();
        void setDrawerLayout();
    }

    interface Presenter{
        void addPagerAdapter();
        void search();
        void addDrawerLayout();
    }

}
