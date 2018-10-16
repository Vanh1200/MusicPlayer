package com.vanh1200.musicapp.presenter.play.playlist;

import android.content.Context;

import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public class PlaylistPresenter implements PlaylistContract.Presenter{
    PlaylistContract.View view;
    private ArrayList<Song> arrSong;
    public PlaylistPresenter(PlaylistContract.View view, ArrayList<Song> arrSong) {
        this.view = view;
        this.arrSong = arrSong;
    }

    @Override
    public void loadData(Context context) {
        view.showList(arrSong);
    }
}
