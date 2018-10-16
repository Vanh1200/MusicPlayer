package com.vanh1200.musicapp.model;

public class Artist {
    private long artistID;
    private String artistName;
    private int numberOfSongs;
    private int numberOfAlbums;

    public Artist() {
    }

    public Artist(long artistID, String artistName, int numberOfSongs, int numberOfAlbums) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.numberOfSongs = numberOfSongs;
        this.numberOfAlbums = numberOfAlbums;
    }

    public long getArtistID() {
        return artistID;
    }

    public void setArtistID(long artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public int getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public void setNumberOfAlbums(int numberOfAlbums) {
        this.numberOfAlbums = numberOfAlbums;
    }
}
