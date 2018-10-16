package com.vanh1200.musicapp.presenter.main.artist;

import android.content.Context;

import com.vanh1200.musicapp.model.Artist;

import java.util.ArrayList;

public interface ArtistContract {
    interface View{
        void displayArtistList(ArrayList<Artist> arrArtist);
    }

    interface Presenter{
        void loadData(Context context);
    }
}
