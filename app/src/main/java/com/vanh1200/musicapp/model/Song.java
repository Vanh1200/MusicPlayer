package com.vanh1200.musicapp.model;

import java.io.Serializable;

public class Song implements Serializable{
    private String name;
    private String album;
    private String artist;
    private long duration;
    private long size;
    private String filePath;
    private String thumbnailPath;
    private long albumID;

    public Song(String name, String artist, long duration, long songID) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.songID = songID;
    }

    private long songID;

    public Song() {
    }

    public Song(long songID, String filePath, String name, String album, String artist, long duration, long size, String thumbnailPath, long albumID) {
        this.songID = songID;
        this.albumID = albumID;
        this.filePath = filePath;
        this.thumbnailPath = thumbnailPath;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
    }


    public String getfilePath() {
        return filePath;
    }

    public void setfilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getalbum() {
        return album;
    }

    public void setalbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public long getSongID() {
        return songID;
    }

    public void setSongID(long songID) {
        this.songID = songID;
    }
}
