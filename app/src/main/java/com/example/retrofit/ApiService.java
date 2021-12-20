package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;


public class ApiService {
    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("khardrix/Albums_db/db")
    Call<AlbumList> getAlbums();
}
