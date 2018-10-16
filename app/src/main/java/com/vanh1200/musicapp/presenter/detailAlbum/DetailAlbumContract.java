package com.vanh1200.musicapp.presenter.detailAlbum;

import android.content.Context;

import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public interface DetailAlbumContract {
    interface View{
        void displaySongList(ArrayList<Song> arrSong);
        void displayInfo(ArrayList<Song> arrSong);
    }

    interface Presenter{
        void loadData(Context context, long id);
    }
}
