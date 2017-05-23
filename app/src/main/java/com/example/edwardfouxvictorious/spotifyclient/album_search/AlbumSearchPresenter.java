package com.example.edwardfouxvictorious.spotifyclient.album_search;


import android.support.annotation.VisibleForTesting;

import com.example.edwardfouxvictorious.spotifyclient.model.AlbumResponse;
import com.example.edwardfouxvictorious.spotifyclient.model.Albums;
import com.example.edwardfouxvictorious.spotifyclient.repository.AlbumSearchRequest;
import com.example.edwardfouxvictorious.spotifyclient.repository.ApiCallback;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class AlbumSearchPresenter {

    private static final String BASE_URL = "https://api.spotify.com/";
    public static final String URL_PATH = "v1/search";
    private static final String Q = "q";
    private static final String TYPE_ALBUM = "album";
    private static final String TYPE = "type";

    @VisibleForTesting
    AlbumSearchView albumSearchView;

    AlbumSearchPresenter(AlbumSearchView albumSearchView) {
        this.albumSearchView = albumSearchView;
    }

    void onCreateCalled(Albums albums) {
        processResponse(albums);
    }

    void provideSearchResults(String searchTerm) {
        if (searchTerm == null || searchTerm.length() == 0) return;
        albumSearchView.showProgressView();
        getData(searchTerm);
    }

    private void getData(String searchTerm) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlbumSearchRequest redditRequest = retrofit.create(AlbumSearchRequest.class);

        Map<String, String> map = new HashMap<>();
        map.put(Q, searchTerm);
        map.put(TYPE, TYPE_ALBUM);
        Call<AlbumResponse> call = redditRequest.getAlbumSearchResult(map);

        call.enqueue(new DataLoaderCallback(this));
    }

    private void processResponse(Albums albums) {
        albumSearchView.hideProgressView();
        albumSearchView.showResult(albums.getItems());
    }

    @VisibleForTesting
    static class DataLoaderCallback extends ApiCallback<AlbumResponse> {
        WeakReference<AlbumSearchPresenter> ref;

        @VisibleForTesting
        DataLoaderCallback(AlbumSearchPresenter presenter) {
            this.ref = new WeakReference<>(presenter);
        }

        @Override
        public void onSuccess(AlbumResponse response) {
            AlbumSearchPresenter albumSearchPresenter = ref.get();
            if (albumSearchPresenter == null) return;

            albumSearchPresenter.processResponse(response.getAlbums());
        }
    }
}
