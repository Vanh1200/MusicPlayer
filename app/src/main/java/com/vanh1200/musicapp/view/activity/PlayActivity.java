package com.vanh1200.musicapp.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.MP3Player;
import com.vanh1200.musicapp.model.Song;
import com.vanh1200.musicapp.presenter.play.adapter.PlaySongAdapter;
import com.vanh1200.musicapp.utils.BlurUtils;
import com.vanh1200.musicapp.utils.KeyUtils;
import com.vanh1200.musicapp.view.fragment.playFragment.LyricsFragment;
import com.vanh1200.musicapp.view.fragment.playFragment.PlayFragment;
import com.vanh1200.musicapp.view.fragment.playFragment.PlayListFragment;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Fragment> arrFragment;
    private PlaySongAdapter adapter;
    private ViewPager viewPager;
    private TextView tvSongName;
    private TextView tvSongArtist;
    private ImageView ivBackBtn;
    private ImageView ivAlarmBtn;
    private RelativeLayout relPlay;

    private ArrayList<Song> arrSong;
    private int currentPosition;

    public MP3Player player;

    public static final int PLAY_LIST = 0;
    public static final int PLAY = 1;
    public static final int LYRICS = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
        addPagerAdapter();
        getIntentAndSetup();
        changeTitle(currentPosition);
        setEvents();
//        playSong(currentPosition);
    }


    private void setEvents() {
        ivBackBtn.setOnClickListener(this);
    }

    public ArrayList<Fragment> getArrFragment() {
        return arrFragment;
    }

    public ArrayList<Song> getArrSong() {
        return arrSong;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void playSong(int position) {
        player.create(position);
        player.start();
    }

    public void changeTitle(int position) {
        tvSongName.setText(arrSong.get(position).getName());
        tvSongArtist.setText(arrSong.get(position).getArtist());
    }

    private void getIntentAndSetup() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra(KeyUtils.KEY_SONG_ARR)){
                arrSong = (ArrayList<Song>) intent.getSerializableExtra(KeyUtils.KEY_SONG_ARR);
            }
            if(intent.hasExtra(KeyUtils.KEY_SONG_POSITION)){
                currentPosition = intent.getIntExtra(KeyUtils.KEY_SONG_POSITION, 0);
            }
        }

//        player = new MP3Player(this, arrSong);

        changeBackground(currentPosition);
    }

    public void changeBackground(int position) {
        String uri = arrSong.get(position).getThumbnailPath();
        Bitmap bgBlur;
        if(uri != null){
            bgBlur = BitmapFactory.decodeFile(uri);
            relPlay.setBackground(new BitmapDrawable(getResources(), BlurUtils.blur(this, bgBlur, 15f)));
        }
        else
            relPlay.setBackgroundResource(R.drawable.bg_play_song_default);
    }


    private void initView() {
        viewPager = findViewById(R.id.view_page_play);
        tvSongName = findViewById(R.id.tv_play_song_title);
        tvSongArtist = findViewById(R.id.tv_play_song_artist);
        ivBackBtn = findViewById(R.id.iv_btn_back);
        ivAlarmBtn = findViewById(R.id.iv_btn_alarm);
        relPlay = findViewById(R.id.rl_play_song);
    }

    private void addPagerAdapter() {
        arrFragment = new ArrayList<>();
        arrFragment.add(new PlayListFragment());
        arrFragment.add(new PlayFragment());
        arrFragment.add(new LyricsFragment());
        adapter = new PlaySongAdapter(getSupportFragmentManager(), arrFragment);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_btn_back:
                onBackPressed();
                break;
            case R.id.iv_btn_alarm:
                break;
        }
    }
}
