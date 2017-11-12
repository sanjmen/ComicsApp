package com.sanjmen.simplecomics.data.source;

import android.support.annotation.NonNull;

import com.sanjmen.simplecomics.data.entities.ComicDataWrapper;

import io.reactivex.Flowable;

/**
 * Main entry point for accessing comics data.
 */
public interface ComicsDataSource {

    Flowable<ComicDataWrapper> getComics(@NonNull Integer offset);

    Flowable<ComicDataWrapper> getComic(@NonNull String taskId);

    void refreshComics();
}
