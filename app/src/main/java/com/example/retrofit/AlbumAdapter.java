package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumHolder> {

    List<Album> albumList = Collections.emptyList();
    Context context;


    public AlbumAdapter(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }


    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_album_row_layout, parent, false);

        return new AlbumHolder(view);
    }


    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {

        // Use the provided View Holder on the onCreateViewHolder method to populate the current
        // row on the RecyclerView
        holder.txtName.setText(albumList.get(position).getUserId());
    }


    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return albumList.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Album data) {
        albumList.add(position, data);
        notifyItemInserted(position);
    }


    // Remove a RecyclerView item containing a specified Data object
    public void remove(Album data) {
        int position = albumList.indexOf(data);
        albumList.remove(position);
        notifyItemRemoved(position);
    }
}
