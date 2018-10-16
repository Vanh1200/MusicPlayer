package com.vanh1200.musicapp.presenter.play.lyrics;

import com.vanh1200.musicapp.model.Song;

public interface LyricsContract {
    interface View{
        void showLyrics(Song song);
    }

    interface Presenter{
        void loadLyrics(Song song);
    }
}
