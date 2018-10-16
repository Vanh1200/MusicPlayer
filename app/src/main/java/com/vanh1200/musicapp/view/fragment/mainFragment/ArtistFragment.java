package com.vanh1200.musicapp.view.fragment.mainFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Artist;
import com.vanh1200.musicapp.presenter.main.artist.ArtistContract;
import com.vanh1200.musicapp.presenter.main.artist.ArtistPresenter;
import com.vanh1200.musicapp.presenter.main.artist.adapter.ArtistAdapter;

import java.util.ArrayList;

public class ArtistFragment extends Fragment implements ArtistContract.View {
    private static ArtistFragment instance;
    private ArtistPresenter presenter;

    private RecyclerView rvArtist;
    private ArtistAdapter adapter;
    private ArrayList<Artist> arrArtist;

    public ArtistFragment() {
        presenter = new ArtistPresenter(this);
    }

    public static final ArtistFragment getInstance(){
        if(instance == null)
            instance = new ArtistFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        presenter.loadData(getActivity());
    }

    private void initViews(View view) {
        rvArtist = view.findViewById(R.id.rv_artist);
    }

    @Override
    public void displayArtistList(ArrayList<Artist> arrArtist) {
        this.arrArtist = arrArtist;
        adapter = new ArtistAdapter(arrArtist, getActivity());
        rvArtist.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvArtist.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvArtist.setAdapter(adapter);
    }
}
