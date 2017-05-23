package com.example.edwardfouxvictorious.spotifyclient.album_search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edwardfouxvictorious.spotifyclient.R;
import com.example.edwardfouxvictorious.spotifyclient.model.Album;
import com.squareup.picasso.Picasso;

class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView textView;
    private ImageView imageView;
    private View view;
    private AlbumSearchAdapter.ItemClickListener itemClickListener;
    private Album album;

    AlbumViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        textView = (TextView) itemView.findViewById(R.id.text_view);
        imageView = (ImageView) itemView.findViewById(R.id.image_album);
    }

    void setup(Album album, AlbumSearchAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        view.setOnClickListener(this);

        textView.setText(album.getName());
        Picasso.with(imageView.getContext()).load(album.getImages().get(0).getUrl()).into(imageView);
        this.album = album;

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemCLicked(album);
    }
}