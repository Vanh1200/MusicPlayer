package com.vanh1200.musicapp.presenter.play.lyrics;

import com.vanh1200.musicapp.model.Song;

public class LyricsPresenter implements LyricsContract.Presenter{
    LyricsContract.View view;

    public LyricsPresenter(LyricsContract.View view) {
        this.view = view;
    }

    @Override
    public void loadLyrics(Song song) {
        view.showLyrics(song);
    }
}
