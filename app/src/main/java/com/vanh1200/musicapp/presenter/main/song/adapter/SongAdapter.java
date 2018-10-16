package com.vanh1200.musicapp.presenter.main.song.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vanh1200.musicapp.R;
import com.vanh1200.musicapp.model.Song;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Song> arrSong; // list song nay vs list song trong fragment la 1.
    private ItemClickListener clickListener;

    public SongAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bindData(arrSong.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        private TextView tvTitle;
        private TextView tvArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        void initViews(View itemView){
            ivThumbnail = itemView.findViewById(R.id.iv_song_icon);
            tvTitle = itemView.findViewById(R.id.tv_song_name);
            tvArtist = itemView.findViewById(R.id.tv_song_artist);
        }

        void bindData(Song song){
            Glide.with(context)
                    .load(song.getThumbnailPath())
                    .into(ivThumbnail);
            tvTitle.setText(song.getName());
            tvArtist.setText(song.getArtist());
        }
    }

    public void update(ArrayList<Song> arrSong){
        this.arrSong = new ArrayList<>(); // if arrSong.clear => delete arr ban dau trong fragment -> dung new
        this.arrSong.addAll(arrSong);
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
