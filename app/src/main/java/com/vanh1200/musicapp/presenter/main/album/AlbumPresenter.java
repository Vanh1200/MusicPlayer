package com.vanh1200.musicapp.presenter.main.album;

import android.content.Context;

import com.vanh1200.musicapp.model.Album;
import com.vanh1200.musicapp.model.MP3Store;

import java.util.ArrayList;

public class AlbumPresenter implements AlbumContract.Presenter{
    private AlbumContract.View view;

    public AlbumPresenter(AlbumContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(Context context) {
        ArrayList<Album> arrAlbum = new MP3Store(context).getAlbumList();
        view.displayAlbumList(arrAlbum);
    }
}
