package com.example.retrofit;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class AlbumHolder extends RecyclerView.ViewHolder  {

    public CardView cardAlbum;
    public TextView txtName;


    public AlbumHolder(View itemView) {
        super(itemView);
        cardAlbum = (CardView) itemView.findViewById(R.id.cardAlbum);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
    }
}
