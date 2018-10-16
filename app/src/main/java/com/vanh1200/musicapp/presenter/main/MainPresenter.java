package com.vanh1200.musicapp.presenter.main;

public class MainPresenter implements MainContract.Presenter {
    public MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void addPagerAdapter() {
        view.setViewPager();
    }

    @Override
    public void search() {
    }

    @Override
    public void addDrawerLayout() {
        view.setDrawerLayout();
    }

}
