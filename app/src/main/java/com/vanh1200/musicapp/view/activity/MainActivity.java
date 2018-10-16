package com.vanh1200.musicapp.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.presenter.main.MainContract;
import com.vanh1200.musicapp.presenter.main.MainPresenter;
import com.vanh1200.musicapp.presenter.main.adapter.ViewpagerAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {
    MainPresenter presenter;

    public Toolbar toolbar;
    private ViewPager viewPager;
    private ViewpagerAdapter adapter;
    private TabLayout tabLayout;
    private NavigationView navMain;
    private DrawerLayout drawerLayoutMain;
    private ActionBarDrawerToggle toggle;

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!checkPermission(permissions))
            return;
        initViews();
    }

    private boolean checkPermission(String[] permissions){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for(String permission : permissions){
                if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(permissions, 0);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(checkPermission(permissions))
            initViews();
        else
            finish();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initViews() {
        toolbar = findViewById(R.id.tb_main);
        viewPager = findViewById(R.id.view_page);
        tabLayout = findViewById(R.id.tab_layout);
        navMain = findViewById(R.id.nav_main);
        drawerLayoutMain = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new MainPresenter(this);
        presenter.addPagerAdapter();
        presenter.addDrawerLayout();
    }

    @Override
    public void setViewPager() {
        String[] arrTitle = {"Songs", "Albums", "Artist"};
        adapter = new ViewpagerAdapter(getSupportFragmentManager(), arrTitle);
        viewPager.setOffscreenPageLimit(ViewpagerAdapter.SIZE);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setDrawerLayout() {
        toggle = new ActionBarDrawerToggle(this, drawerLayoutMain, toolbar, R.string.open, R.string.close);
        drawerLayoutMain.addDrawerListener(toggle);
        toggle.syncState();
        navMain.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayoutMain.closeDrawer(Gravity.START);
        switch (item.getItemId()){
            case R.id.menu_songs:
                viewPager.setCurrentItem(0);
                break;
            case R.id.menu_albums:
                viewPager.setCurrentItem(1);
                break;
            case R.id.menu_artists:
                viewPager.setCurrentItem(2);
                break;
            case R.id.menu_about_author:
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setMessage("Vanh1200");
                dialog.show();
        }
        return true;
    }


}
