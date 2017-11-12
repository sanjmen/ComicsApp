package com.sanjmen.simplecomics.data.source;

import android.support.annotation.NonNull;

import com.sanjmen.simplecomics.BuildConfig;
import com.sanjmen.simplecomics.data.DataModule;
import com.sanjmen.simplecomics.data.api.RestApiService;
import com.sanjmen.simplecomics.data.entities.ComicDataWrapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;


/**
 * Concrete implementation to load comics from marvel Rest API.
 * <p/>
 */
@Singleton
public class ComicsRepository implements ComicsDataSource {

    @NonNull
    private final RestApiService restApiService;

    /**
     * By marking the constructor with {@code @Inject}, Dagger will try to inject the dependencies
     * required to create an instance of the TasksRepository. Because {@link ComicsDataSource} is an
     * interface, we must provide to Dagger a way to build those arguments, this is done in
     * {@link DataModule}.
     * <p>
     * When two arguments or more have the same type, we must provide to Dagger a way to
     * differentiate them. This is done using a qualifier.
     * <p>
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    public ComicsRepository(@NonNull RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    /**
     * Gets comics from api.
     *
     * @param offset int, The requested offset (skipped results) of the call
     */
    @Override
    public Flowable<ComicDataWrapper> getComics(@NonNull Integer offset) {
        return restApiService.comics(BuildConfig.API_RESULTS_ORDER_BY, BuildConfig.API_RESULTS_LIMIT, offset);
    }

    /**
     * Gets comic from api.
     *
     * @param comicId string, the id of the comic to get details.
     */
    @Override
    public Flowable<ComicDataWrapper> getComic(@NonNull String comicId) {
        return null;
    }

    /**
     * Get fresh comics data from api.
     */
    @Override
    public void refreshComics() {
        restApiService.comics(BuildConfig.API_RESULTS_ORDER_BY, BuildConfig.API_RESULTS_LIMIT, 0);
    }
}
