package com.sanjmen.simplecomics.comics;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanjmen.simplecomics.R;
import com.sanjmen.simplecomics.data.entities.Comic;
import com.sanjmen.simplecomics.utils.DeviceDimensionsHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsViewHolder> {

    private final List<Comic> comicList;

    private final Listener listener;

    private final Picasso picasso;

    private final DeviceDimensionsHelper deviceDimensionsHelper;


    public ComicsAdapter(@NonNull Fragment fragment, @NonNull Picasso picasso, @NonNull DeviceDimensionsHelper deviceDimensionsHelper) {
        this.comicList = new ArrayList<>();
        this.listener = (Listener) fragment;
        this.picasso = picasso;
        this.deviceDimensionsHelper = deviceDimensionsHelper;
    }

    @Override
    public ComicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item, parent, false);
        return new ComicsViewHolder(view, picasso, deviceDimensionsHelper);
    }

    @Override
    public void onBindViewHolder(ComicsViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            listener.onScrolledToBottom(position);
        }
        Comic comic = comicList.get(position);
        holder.bind(comic);
        holder.rootView.setOnClickListener(view -> listener.onComicClick(comic));
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public void setData(@NonNull List<Comic> comicList) {
        this.comicList.addAll(comicList);
        notifyItemRangeInserted(getItemCount(), comicList.size() - 1);
    }

    public void removeData() {
        comicList.clear();
        notifyDataSetChanged();
    }

    public interface Listener {
        void onComicClick(Comic comic);
        void onScrolledToBottom(int position);
    }
}
