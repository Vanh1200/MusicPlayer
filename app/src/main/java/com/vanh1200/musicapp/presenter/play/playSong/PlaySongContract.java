package com.vanh1200.musicapp.presenter.play.playSong;

public interface PlaySongContract {
    interface View{
        void initSeekBar();
        void updateSeekBar();
        void showDish();
    }

    interface Presenter{
        void loadData();
    }
}
