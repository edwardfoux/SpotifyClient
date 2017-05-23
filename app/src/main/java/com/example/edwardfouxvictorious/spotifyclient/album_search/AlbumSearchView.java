package com.example.edwardfouxvictorious.spotifyclient.album_search;

import com.example.edwardfouxvictorious.spotifyclient.model.Album;

import java.util.List;

interface AlbumSearchView {
    void showResult(List<Album> albumList);
}
