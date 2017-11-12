package com.sanjmen.simplecomics.data.api;

import com.sanjmen.simplecomics.data.entities.ComicDataWrapper;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {

    /**
     * Fetches lists of comics
     *
     * @param orderBy Order the result set by a field or fields.
     * @param limit   Limit the result set to the specified number of resources.
     * @param offset  Skip the specified number of resources in the result set.
     * @return
     */
    @GET("comics")
    Flowable<ComicDataWrapper> comics(
            @Query("orderBy") String orderBy,
            @Query("limit") int limit,
            @Query("offset") int offset);

    @GET("comics/{id}")
    Flowable<ComicDataWrapper> comic(
            @Query("id") int id
    );


}
