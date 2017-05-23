package com.example.edwardfouxvictorious.spotifyclient.album_search;

import com.example.edwardfouxvictorious.spotifyclient.model.Album;
import com.example.edwardfouxvictorious.spotifyclient.model.AlbumResponse;
import com.example.edwardfouxvictorious.spotifyclient.model.Albums;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.ArrayList;

public class AlbumSearchPresenterTest {
    private AlbumSearchPresenter presenter;

    private AlbumSearchView albumSearchView;

    @Before
    public void setup() {
        presenter = new AlbumSearchPresenter(albumSearchView);
        albumSearchView = Mockito.mock(AlbumSearchView.class);
        presenter.albumSearchView = albumSearchView;
    }

    @Test
    public void onDataAvailableTest() {
        AlbumResponse albumResponse = Mockito.mock(AlbumResponse.class);
        Albums albums = Mockito.mock(Albums.class);
        Mockito.when(albumResponse.getAlbums()).thenReturn(albums);
        Mockito.when(albums.getItems()).thenReturn(new ArrayList<Album>());
        AlbumSearchPresenter.DataLoaderCallback dataLoaderCallback = new AlbumSearchPresenter.DataLoaderCallback(presenter);

        dataLoaderCallback.onSuccess(albumResponse);

        Mockito.verify(albumSearchView, Mockito.times(1)).showResult(Matchers.anyListOf(Album.class));
    }

    @Test
    public void configurationChange() {
        Albums albums = Mockito.mock(Albums.class);
        Mockito.when(albums.getItems()).thenReturn(new ArrayList<Album>());

        presenter.onCreateCalled(albums);

        Mockito.verify(albumSearchView, Mockito.times(1)).showResult(Matchers.anyListOf(Album.class));
        Mockito.verify(albumSearchView, Mockito.times(1)).hideProgressView();
    }

    @Test
    public void showProgress() {
        presenter.provideSearchResults("search");

        Mockito.verify(albumSearchView, Mockito.times(1)).showProgressView();
    }
}
