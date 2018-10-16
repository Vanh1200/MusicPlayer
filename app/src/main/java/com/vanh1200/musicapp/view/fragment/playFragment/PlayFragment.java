package com.vanh1200.musicapp.view.fragment.playFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Song;
import com.vanh1200.musicapp.view.activity.PlayActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayFragment extends Fragment{
    private CircleImageView civPlaySong;
    private ArrayList<Song> arrSong;
    private int currentPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        initView(view);
        loadInfoFromActivity();
        return view;
    }


    public void loadInfoFromActivity() {
        arrSong = ((PlayActivity) getActivity()).getArrSong();
        currentPosition = ((PlayActivity) getActivity()).getCurrentPosition();
        if(getActivity() != null){
            if(arrSong.get(currentPosition).getThumbnailPath() != null)
                Glide.with(getActivity())
                        .load(arrSong.get(currentPosition).getThumbnailPath())
                        .into(civPlaySong);
            Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anim);
            civPlaySong.startAnimation(rotate);
        }
    }

    public void loadInfoFromFragment(int position){
        currentPosition = position;
        if(getActivity() != null){
            if(arrSong.get(position).getThumbnailPath() != null)
                Glide.with(getActivity())
                        .load(arrSong.get(position).getThumbnailPath())
                        .into(civPlaySong);
            else
                civPlaySong.setImageResource(R.drawable.bg_music_album_final);
            Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anim);
            civPlaySong.startAnimation(rotate);
        }
    }

    private void initView(View view) {
        civPlaySong = view.findViewById(R.id.civ_play_song);
    }
}
