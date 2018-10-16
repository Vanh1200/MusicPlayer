package com.vanh1200.musicapp.presenter.main.album;

import android.content.Context;

import com.vanh1200.musicapp.model.Album;

import java.util.ArrayList;

public interface AlbumContract {
    public interface View{
        void displayAlbumList(ArrayList<Album> arrAlbum);
    }

    public interface Presenter{
        void loadData(Context context);
    }
}
