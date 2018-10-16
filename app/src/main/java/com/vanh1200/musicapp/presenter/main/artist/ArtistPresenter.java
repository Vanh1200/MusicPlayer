package com.vanh1200.musicapp.presenter.main.artist;

import android.content.Context;

import com.vanh1200.musicapp.model.Artist;
import com.vanh1200.musicapp.model.MP3Store;

import java.util.ArrayList;

public class ArtistPresenter implements ArtistContract.Presenter{
    private ArtistContract.View view;

    public ArtistPresenter(ArtistContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(Context context) {
        ArrayList<Artist> arrArtist  = new MP3Store(context).getArtistList();
        view.displayArtistList(arrArtist);
    }
}
