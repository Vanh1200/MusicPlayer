package com.vanh1200.musicapp.presenter.play;

public interface PlayContract {
    interface View{
        void nextSong();
        void preSong();
        void playOrPauseSong();
    }

    interface Presenter{
        void playButtonClick();
        void preButtonCLick();
        void nextButtonClick();
    }
}
