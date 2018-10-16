package com.vanh1200.musicapp.presenter.play.playSong;

public class PlaySongPresenter implements PlaySongContract.Presenter{
    PlaySongContract.View view;

    public PlaySongPresenter(PlaySongContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {

    }
}
