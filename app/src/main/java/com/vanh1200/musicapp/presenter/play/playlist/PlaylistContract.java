package com.vanh1200.musicapp.presenter.play.playlist;

import android.content.Context;

import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public interface PlaylistContract {
    interface View{
        void showList(ArrayList<Song> arrSong);
    }

    interface Presenter{
        void loadData(Context context);
    }
}
