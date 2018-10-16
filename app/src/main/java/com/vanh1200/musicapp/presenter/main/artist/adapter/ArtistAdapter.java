package com.vanh1200.musicapp.presenter.main.artist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Artist;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder>{
    private ArrayList<Artist> arrArtist;
    private Context context;

    public ArtistAdapter(ArrayList<Artist> arrArtist, Context context) {
        this.arrArtist = arrArtist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.artist_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(arrArtist.get(i));
    }

    @Override
    public int getItemCount() {
        return arrArtist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvNumAlbums;
        private TextView tvNumSongs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            tvName = itemView.findViewById(R.id.tv_artist_name);
            tvNumAlbums = itemView.findViewById(R.id.tv_artist_album_nubmer);
            tvNumSongs = itemView.findViewById(R.id.tv_artist_song_nubmer);
        }

        public void bindData(Artist artist){
            tvName.setText(artist.getArtistName());
            tvNumSongs.setText(String.valueOf(artist.getNumberOfSongs()));
            tvNumAlbums.setText(String.valueOf(artist.getNumberOfAlbums()));
        }
    }
}
