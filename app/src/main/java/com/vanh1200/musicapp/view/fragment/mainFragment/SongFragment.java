package com.vanh1200.musicapp.view.fragment.mainFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.security.keystore.KeyNotYetValidException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.MP3Player;
import com.vanh1200.musicapp.model.Song;
import com.vanh1200.musicapp.presenter.main.song.SongContract;
import com.vanh1200.musicapp.presenter.main.song.SongPresenter;
import com.vanh1200.musicapp.presenter.main.song.adapter.SongAdapter;
import com.vanh1200.musicapp.utils.KeyUtils;
import com.vanh1200.musicapp.view.activity.PlayActivity;

import java.util.ArrayList;

public class SongFragment extends Fragment implements SongContract.View, SearchView.OnQueryTextListener, SongAdapter.ItemClickListener {
    private static SongFragment instance;
    private SongPresenter presenter;

    private ArrayList<Song> arrSong;
    private RecyclerView rvSong;
    private SongAdapter adapter;
    private SearchView searchView;

    private MP3Player player;


    public SongFragment() {
        presenter = new SongPresenter(this);
    }

    public static final SongFragment getInstance(){
        if(instance == null)
            instance = new SongFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        presenter.loadData(getActivity());
    }

    private void initViews(View view) {
        setHasOptionsMenu(true);
        rvSong = view.findViewById(R.id.rv_song);
    }

    @Override
    public void displaySongList(ArrayList<Song> arrayList) {
        this.arrSong = arrayList;
        adapter = new SongAdapter(getActivity(), arrSong);
        rvSong.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSong.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvSong.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<Song> arrSong = getFiledArrSong(newText);
        adapter.update(arrSong);
        return true;
    }

    public ArrayList<Song> getFiledArrSong(String query) {
        ArrayList<Song> arrSongNew = new ArrayList<>();
        for(Song song: this.arrSong){
            if(song.getName().toLowerCase().contains(query.toLowerCase()))
                arrSongNew.add(song);
        }
        return arrSongNew;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_toolbar, menu);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), PlayActivity.class);
        intent.putExtra(KeyUtils.KEY_SONG_ARR, arrSong);
        intent.putExtra(KeyUtils.KEY_SONG_POSITION, position);
        startActivity(intent);
    }

    public class AsyncTaskPlayer extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            while(true){
                publishProgress();
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

    }
}
