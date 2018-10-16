package com.vanh1200.musicapp.view.fragment.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Album;
import com.vanh1200.musicapp.presenter.main.album.AlbumContract;
import com.vanh1200.musicapp.presenter.main.album.AlbumPresenter;
import com.vanh1200.musicapp.presenter.main.album.adapter.AlbumAdapter;
import com.vanh1200.musicapp.utils.KeyUtils;
import com.vanh1200.musicapp.view.activity.DetailAlbumActivity;

import java.util.ArrayList;

public class AlbumFragment extends Fragment implements AlbumContract.View, AlbumAdapter.OnClick {
    private static AlbumFragment instance;
    private AlbumPresenter presenter;

    private RecyclerView rvAlbum;
    private AlbumAdapter adapter;
    private ArrayList<Album> arrAlbum;

    public AlbumFragment() {
        presenter = new AlbumPresenter(this);
    }

    public static final AlbumFragment getInstance(){
        if(instance == null)
            instance = new AlbumFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        presenter.loadData(getActivity());
    }

    private void initViews(View view) {
        rvAlbum = view.findViewById(R.id.rv_album);
    }

    @Override
    public void displayAlbumList(ArrayList<Album> arrAlbum) {
        this.arrAlbum = arrAlbum;
        adapter = new AlbumAdapter(arrAlbum, getActivity());
        adapter.setOnClick(this);
        rvAlbum.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvAlbum.setAdapter(adapter);
    }

    @Override
    public void onItemClick(long albumID) {
        Intent intent = new Intent(getActivity(), DetailAlbumActivity.class);
        intent.putExtra(KeyUtils.KEY_ALBUM_ID, albumID);
        startActivity(intent);
    }
}
