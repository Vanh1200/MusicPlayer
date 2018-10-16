package com.vanh1200.musicapp.presenter.detailAlbum.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Song;
import com.vanh1200.musicapp.utils.ConvertUtils;

import java.util.ArrayList;

import jp.wasabeef.blurry.Blurry;

public class DetailAlbumAdapter extends RecyclerView.Adapter<DetailAlbumAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Song> arrSong;

    public DetailAlbumAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item_in_album, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(arrSong.get(i));
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        private TextView tvTitle;
        private TextView tvArtist;
        private TextView tvDuarion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        void initViews(View itemView){
            ivThumbnail = itemView.findViewById(R.id.iv_song_icon_album);
            tvTitle = itemView.findViewById(R.id.tv_song_name_album);
            tvArtist = itemView.findViewById(R.id.tv_song_artist_album);
            tvDuarion = itemView.findViewById(R.id.tv_duration_song_album);
        }

        void bindData(Song song){
            Glide.with(context)
                    .load(song.getThumbnailPath())
                    .into(ivThumbnail);
            tvTitle.setText(song.getName());
            tvArtist.setText(song.getArtist());
            tvDuarion.setText(ConvertUtils.longToTime(song.getDuration()));

        }
    }
}
