package com.vanh1200.musicapp.presenter.play.playlist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Song> arrSong;
    private OnItemClickCallBack onItemClickCallBack;

    public PlaylistAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bindData(arrSong.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickCallBack != null){
                    onItemClickCallBack.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSongName;
        private TextView tvSongArtist;
        private TextView tvSeqNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            tvSongName = itemView.findViewById(R.id.tv_song_name_playlist);
            tvSongArtist = itemView.findViewById(R.id.tv_song_artist_playlist);
            tvSeqNumber = itemView.findViewById(R.id.tv_seq_number);
        }

        private void bindData(Song song){
            tvSongName.setText(song.getName());
            tvSongArtist.setText(song.getArtist());
            tvSeqNumber.setText(String.valueOf(arrSong.indexOf(song)));
        }
    }

    public interface OnItemClickCallBack{
        void onItemClick(int postion);
    }
}
