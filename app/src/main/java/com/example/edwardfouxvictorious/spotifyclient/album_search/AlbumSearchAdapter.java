package com.example.edwardfouxvictorious.spotifyclient.album_search;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.edwardfouxvictorious.spotifyclient.R;
import com.example.edwardfouxvictorious.spotifyclient.model.Album;

import java.util.ArrayList;
import java.util.List;

class AlbumSearchAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    private List<Album> list = new ArrayList<>();
    ItemClickListener itemClickListener;

    AlbumSearchAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    void setList(List<Album> list) {
        this.list = list;
    }

    List<Album> getList() {
        return list;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_view_holder, null);
        WindowManager windowManager = (WindowManager) parent.getContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        int width = point.x;
        view.setLayoutParams(new LinearLayoutCompat.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT));
        return new AlbumViewHolder(view);
    }

    @Override
    @VisibleForTesting
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = list.get(position);
        holder.setup(album, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface ItemClickListener {
        void onItemCLicked(Album album);
    }
}