package com.vanh1200.musicapp.model;

import java.util.ArrayList;

public class Album {
    private long albumID;
    private String albumName;
    private String albumArtist;
    private String albumImage;

    public Album() {
    }

    public Album(long albumID, String albumName, String albumArtist, String albumImage) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.albumImage = albumImage;
    }

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }
}
