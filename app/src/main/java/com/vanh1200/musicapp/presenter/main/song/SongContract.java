package com.vanh1200.musicapp.presenter.main.song;

import android.content.Context;

import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public interface SongContract {
    interface View{
        void displaySongList(ArrayList<Song> arrayList);
    }

    interface Presenter{
        void loadData(Context context);
//        void playSong();
    }
}
