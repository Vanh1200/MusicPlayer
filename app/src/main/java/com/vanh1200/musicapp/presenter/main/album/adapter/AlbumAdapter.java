package com.vanh1200.musicapp.presenter.main.album.adapter;

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
import com.vanh1200.musicapp.model.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    private ArrayList<Album> arrAlbum;
    private Context context;
    private OnClick onClick;

    public AlbumAdapter(ArrayList<Album> arrAlbum, Context context) {
        this.arrAlbum = arrAlbum;
        this.context = context;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_item,viewGroup,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.bindData(arrAlbum.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick != null){
                    onClick.onItemClick(arrAlbum.get(i).getAlbumID());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAlbum;
        private TextView tvAlbumName;
        private TextView tvAlbumArtist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            ivAlbum = itemView.findViewById(R.id.iv_grid_album);
            tvAlbumName = itemView.findViewById(R.id.tv_album_name);
            tvAlbumArtist = itemView.findViewById(R.id.tv_album_artist);
        }

        public void bindData(Album album){
            Glide.with(context)
                    .load(album.getAlbumImage())
                    .into(ivAlbum);
            tvAlbumName.setText(album.getAlbumName());
            tvAlbumArtist.setText(album.getAlbumArtist());
        }
    }

    public interface OnClick{
        void onItemClick(long albumID);
    }
}
