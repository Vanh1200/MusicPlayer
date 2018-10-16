package com.vanh1200.musicapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AndroidException;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Song;
import com.vanh1200.musicapp.presenter.detailAlbum.DetailAlbumContract;
import com.vanh1200.musicapp.presenter.detailAlbum.DetailAlbumPresenter;
import com.vanh1200.musicapp.presenter.detailAlbum.adapter.DetailAlbumAdapter;
import com.vanh1200.musicapp.utils.KeyUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.blurry.Blurry;

public class DetailAlbumActivity extends AppCompatActivity implements DetailAlbumContract.View{
    private DetailAlbumPresenter presenter;

    private AppBarLayout appBarLayout;
    private LinearLayout lnInfo;
    private Toolbar toolbar;
    private TextView tvAlbumName;
    private TextView tvArtistName;
    private TextView tvNumOfSongs;
    private RecyclerView rvDetailAlbum;
    private CircleImageView ivCircle;
    private ImageView ivBackground;
    private DetailAlbumAdapter adapter;
    private ArrayList<Song> arrSong;
//    private

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_album);
        initViews();
        presenter = new DetailAlbumPresenter(this);
        long id = getSentIntent();
        presenter.loadData(this,id);
    }

    private long getSentIntent() {
        Intent intent = getIntent();
        if(intent!= null){
            if(intent.hasExtra(KeyUtils.KEY_ALBUM_ID)){
                return intent.getLongExtra(KeyUtils.KEY_ALBUM_ID, 0);
            }
        }
        return 0;
    }

    private void initViews() {
        appBarLayout = findViewById(R.id.appbar_layout);
        lnInfo = findViewById(R.id.ln_detail);
        toolbar = findViewById(R.id.toolbar_detal_album);
        tvAlbumName = findViewById(R.id.tv_detail_name_song);
        tvArtistName = findViewById(R.id.tv_detail_name_artist);
        tvNumOfSongs = findViewById(R.id.tv_detail_numSongs_artist);
        rvDetailAlbum = findViewById(R.id.rv_detail_alabum);
        ivCircle = findViewById(R.id.iv_circle_detail);
        ivBackground = findViewById(R.id.iv_background);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        makeFadeAnim();
    }

    private void makeFadeAnim() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                lnInfo.setAlpha(1.0f - Math.abs(verticalOffset / (float)
                        appBarLayout.getTotalScrollRange()));


            }
        });
    }

    @Override
    public void displaySongList(ArrayList<Song> arrSong) {
        this.arrSong = arrSong;
        adapter = new DetailAlbumAdapter(this, arrSong);
        rvDetailAlbum.setLayoutManager(new LinearLayoutManager(this));
        rvDetailAlbum.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvDetailAlbum.setAdapter(adapter);
    }

    @Override
    public void displayInfo(ArrayList<Song> arrSong) {
        Song song = arrSong.get(0);
        long totalLength  = getTotalLength(arrSong);
        tvArtistName.setText(song.getArtist());
        tvAlbumName.setText(song.getalbum());
        tvNumOfSongs.setText(String.valueOf(arrSong.size()));
        setTitle(song.getalbum());
        setTitle(song.getalbum());

        Glide.with(this)
                .load(song.getThumbnailPath())
                .into(ivBackground);
        if (song.getThumbnailPath() != null)
            Glide.with(this)
                .load(song.getThumbnailPath())
                .into(ivCircle);
    }

    private long getTotalLength(ArrayList<Song> arrSong){
        long total = 0;
        for (Song song: arrSong){
            total += song.getDuration();
        }
        return total;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_album_toolbar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
