package com.example.edwardfouxvictorious.spotifyclient.model;

public class AlbumResponse {
    private Albums albums;

    public Albums getAlbums() {
        return albums;
    }

    @Override
    public String toString() {
        return "AlbumResponse{" +
                "albums=" + albums +
                '}';
    }
}
