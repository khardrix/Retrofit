package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class AlbumList {
    @SerializedName("albums")
    @Expose
    private ArrayList<Album> albums = null;


    public ArrayList<Album> getAlbums() {
        return albums;
    }


    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
