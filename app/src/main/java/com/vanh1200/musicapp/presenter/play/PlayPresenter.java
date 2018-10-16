package com.vanh1200.musicapp.presenter.play;

public class PlayPresenter implements PlayContract.Presenter{
    private PlayContract.View view;

    public PlayPresenter(PlayContract.View view) {
        this.view = view;
    }

    @Override
    public void playButtonClick() {
        view.playOrPauseSong();
    }

    @Override
    public void preButtonCLick() {
        view.preSong();
    }

    @Override
    public void nextButtonClick() {
        view.nextSong();
    }
}
