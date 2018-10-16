package com.vanh1200.musicapp.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IntDef;

import java.util.ArrayList;

public class MP3Player implements MediaPlayer.OnCompletionListener {
    private MediaPlayer player; // object dung de phat nhac: ca onl va offline
    private ArrayList<Song> arrSong;
    private Context context;
    private int currentIndex;


    public MP3Player(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    public void create(int index){
        release();
        this.currentIndex = index;
        Uri uri = Uri.parse(arrSong.get(index).getfilePath());
        player = MediaPlayer.create(context, uri);
        player.setOnCompletionListener(this);
    }

//    public void create (Song song){
//        release();;
//        this
//    }

    public void start(){
        if(player != null)
            player.start();
    }

    public void pause(){
        if (player != null)
            player.pause();
    }

    public void stop(){
        if(player != null)
            player.stop();
    }

    public void release(){
        if(player != null)
            player.release();
    }

    public void loop(boolean isLoop){
        if(player != null)
            player.setLooping(isLoop);
    }

    public void seek(int duration){
        if(player != null)
            player.seekTo(duration);
    }

    public static final int NEXT = 1;
    public static final int PREV = -1;

    @IntDef({NEXT, PREV})
    @interface SongPosition{}
    public void changeSong(@SongPosition int value){
        currentIndex += value;
        if(currentIndex >= arrSong.size()){
            currentIndex = 0;
        }
        else if(currentIndex < 0){
            currentIndex = arrSong.size() - 1;
        }
    }

    public int getDuration(){
        if(player != null)
            return player.getDuration();
        return 0;
    }

    public int getCurrentPosition(){
        if(player != null)
            return player.getCurrentPosition();
        return 0;
    }

    public String getName(){
        if(arrSong.size() >= currentIndex){
            return arrSong.get(currentIndex).getName();
        }
        return "";
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        changeSong(NEXT);
    }
}
