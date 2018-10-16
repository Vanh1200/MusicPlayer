package com.vanh1200.musicapp.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.util.MonthDisplayHelper;

import java.util.ArrayList;


public class MP3Store {
    private static final String TAG = "MP3Store";
    private ContentResolver resolver;
    Context context;

    public MP3Store(Context context) {
        this.context = context;
        this.resolver = context.getContentResolver();
    }

    public ArrayList<Song> getData() {
        ArrayList<Song> arrSong = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        int indexSongID = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
        int indexData = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int indexTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexSize = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
        int indexDuration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int indexAlbum = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        int indexAlbumID = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        if(cursor != null){
            do{
                long id  = cursor.getLong(indexSongID);
                String data = cursor.getString(indexData);
                String title = cursor.getString(indexTitle);
                String artist = cursor.getString(indexArtist);
                long duration = cursor.getLong(indexDuration);
                String album = cursor.getString(indexAlbum);
                long size = cursor.getLong(indexSize);
                long albumID = cursor.getLong(indexAlbumID);
                Song song = new Song(id, data, title, album, artist, duration, size, "", albumID);
                arrSong.add(song);
                Log.d(TAG, "getData: " + id );
            }while (cursor.moveToNext());
        }
        cursor.close();

        // thumbnails were in MediaStore.Audio.Albums
        for (int i = 0; i < arrSong.size(); i++) {
            cursor = resolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Audio.Albums.ALBUM_ART},
                    MediaStore.Audio.Albums._ID + " = ?",
                    new String[]{Long.toString(arrSong.get(i).getAlbumID())},
                    null);
            if (cursor.moveToFirst()) {
                arrSong.get(i).setThumbnailPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)));
            }
            cursor.close();
        }
        return arrSong;
    }

    public ArrayList<Album> getAlbumList(){
        ArrayList<Album> arrAlbum = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.Albums.ALBUM + " ASC");
        cursor.moveToFirst();
        int indexAlbumID = cursor.getColumnIndex(MediaStore.Audio.Albums._ID);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
        int indexAlbumName = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
        int indexImage = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
        long indexID = cursor.getLong(indexAlbumID);
        if(cursor != null) {
            do {
                long id = cursor.getLong(indexAlbumID);
                String artist = cursor.getString(indexArtist);
                String name = cursor.getString(indexAlbumName);
                String image = cursor.getString(indexImage);
                Album album = new Album(id, name, artist, image);
                arrAlbum.add(album);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrAlbum;
    }

    public ArrayList<Song> getSongByAlbumID(long albumID){
        ArrayList<Song> arrSong = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                MediaStore.Audio.Media.ALBUM_ID + " = ?",
                new String[]{String.valueOf(albumID)},
                null);
        cursor.moveToFirst();
        int indexID = cursor.getColumnIndex(MediaStore.Audio.Albums._ID);
        int indexTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int indexDuration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int indexAlbumID = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        int indexAlbumName = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

        if(cursor != null){
            do{
                long id = cursor.getLong(indexID);
                String title = cursor.getString(indexTitle);
                long duration = cursor.getLong(indexDuration);
                String artist = cursor.getString(indexArtist);
                String albumName = cursor.getString(indexAlbumName);
                Song song = new Song(title, artist, duration, id);
                song.setAlbumID(cursor.getLong(indexAlbumID));
                song.setalbum(albumName);
                arrSong.add(song);
            }while (cursor.moveToNext());
            cursor.close();
        }

        // thumbnails were in MediaStore.Audio.Albums
        for (int i = 0; i < arrSong.size(); i++) {
            cursor = resolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Audio.Albums.ALBUM_ART},
                    MediaStore.Audio.Albums._ID + " = ?",
                    new String[]{Long.toString(arrSong.get(i).getAlbumID())},
                    null);
            if (cursor.moveToFirst()) {
                arrSong.get(i).setThumbnailPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART)));
            }
            cursor.close();
        }
        return arrSong;
    }

    public ArrayList<Artist> getArtistList(){
        ArrayList<Artist> arrArtist = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        int indexID = cursor.getColumnIndex(MediaStore.Audio.Artists._ID);
        int indexName = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
        int indexNumSong = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS);
        int indexNumAlbum = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);
        if (cursor != null){
            do{
                long id = cursor.getLong(indexID);
                String name = cursor.getString(indexName);
                int numSongs = cursor.getInt(indexNumSong);
                int numAlbums = cursor.getInt(indexNumAlbum);
                Artist artist = new Artist(id, name, numSongs, numAlbums);
                arrArtist.add(artist);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return arrArtist;
    }
}
