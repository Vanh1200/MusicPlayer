package com.vanh1200.musicapp.view.fragment.playFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Song;
import com.vanh1200.musicapp.presenter.play.playlist.adapter.PlaylistAdapter;
import com.vanh1200.musicapp.view.activity.PlayActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayListFragment extends Fragment implements PlaylistAdapter.OnItemClickCallBack {
    private CircleImageView civPlaySong;
    private ArrayList<Song> arrSong;
    private int currentPosition;

    private RecyclerView rcvPlaylist;
    private PlaylistAdapter adapter;

    public PlayListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        initViews(view);
        loadInfo();
        addRecyclerView();
        return view;
    }

    private void initViews(View view) {
        rcvPlaylist = view.findViewById(R.id.rcv_playlist);
    }

    private void addRecyclerView() {
        adapter = new PlaylistAdapter(getActivity(), arrSong);
        adapter.setOnItemClickCallBack(this);
        rcvPlaylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvPlaylist.setAdapter(adapter);
    }

    private void loadInfo() {
        arrSong = ((PlayActivity) getActivity()).getArrSong();
        currentPosition = ((PlayActivity) getActivity()).getCurrentPosition();
    }

    @Override
    public void onItemClick(int postion) {
        PlayActivity playActivity = (PlayActivity) getActivity();
        playActivity.changeBackground(postion);
        playActivity.changeTitle(postion);

        PlayFragment playFragment = (PlayFragment) playActivity.getArrFragment().get(PlayActivity.PLAY);
        playFragment.loadInfoFromFragment(postion);
    }
}
