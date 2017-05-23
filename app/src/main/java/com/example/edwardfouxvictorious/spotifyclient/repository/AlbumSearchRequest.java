package com.example.edwardfouxvictorious.spotifyclient.repository;

import com.example.edwardfouxvictorious.spotifyclient.album_search.AlbumSearchPresenter;
import com.example.edwardfouxvictorious.spotifyclient.model.AlbumResponse;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface AlbumSearchRequest {
        @GET(AlbumSearchPresenter.URL_PATH)
        Call<AlbumResponse> getAlbumSearchResult(@QueryMap Map<String, String> search);
    }
