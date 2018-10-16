package com.vanh1200.musicapp.presenter.detailAlbum;

import android.content.Context;

import com.vanh1200.musicapp.model.MP3Store;
import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public class DetailAlbumPresenter implements DetailAlbumContract.Presenter{
    private DetailAlbumContract.View view;

    public DetailAlbumPresenter(DetailAlbumContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(Context context, long id) {
        ArrayList<Song> arrSongInAlbum = new MP3Store(context).getSongByAlbumID(id);
        view.displayInfo(arrSongInAlbum);
        view.displaySongList(arrSongInAlbum);
    }
}
