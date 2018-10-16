package com.vanh1200.musicapp.presenter.main.song;

import android.content.Context;

import com.vanh1200.musicapp.model.MP3Store;
import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public class SongPresenter implements SongContract.Presenter{
    private SongContract.View view;

    public SongPresenter(SongContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(Context context) {
        ArrayList<Song> arrSong = new MP3Store(context).getData();
        view.displaySongList(arrSong);
    }
}
