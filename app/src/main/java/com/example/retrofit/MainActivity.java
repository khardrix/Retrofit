package com.example.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Album> albumList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadAlbumsData();
    }


    protected void loadAlbumsData() {

        /**
         * Checking Internet Connection
         */
        if (InternetConnection.checkConnection(getApplicationContext())) {

            final ProgressDialog dialog;

            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Getting JSON data");
            dialog.setMessage("Please wait...");
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<AlbumList> call = api.getAlbums();
            boolean b = true;
            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<AlbumList>() {
                @Override
                public void onResponse(Call<AlbumList> call, Response<AlbumList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if(response.isSuccessful()) {

                        /**
                         * Got Successfully
                         */
                        // Log.d("LOGGG", "onResponse: " + response.body().getContacts());
                        albumList = response.body().getAlbums();

                        /**
                         * Binding that List to Adapter
                         */

                        RecyclerView recyclerContacts = (RecyclerView) findViewById(
                                R.id.recyclerview_Albums);
                        AlbumAdapter adapter = new AlbumAdapter(albumList,
                                MainActivity.this);
                        recyclerContacts.setAdapter(adapter);
                        recyclerContacts.setLayoutManager(new LinearLayoutManager(
                                MainActivity.this));

                        /**
                         * Add listener to every recycler view items
                         */
                        recyclerContacts.setOnClickListener(MainActivity.this::onClick);
                        /*recyclerContacts.addOnItemTouchListener(new CustomRVItemTouchListener(MainActivity.this, recyclerContacts, new RecyclerViewItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Snackbar.make(findViewById(R.id.layoutMain), "onClick at position : " + position, Snackbar.LENGTH_LONG).show();
                            }
                            @Override
                            public void onLongClick(View view, int position) {
                                Snackbar.make(findViewById(R.id.layoutMain), "onLongClick at position : " + position, Snackbar.LENGTH_LONG).show();
                            }
                        }));*/

                    } else {
                        Snackbar.make(findViewById(R.id.layoutMain), "Something going wrong!",
                                Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<AlbumList> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            Snackbar.make(findViewById(R.id.layoutMain), "Check your internet connection!",
                    Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}