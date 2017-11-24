package com.sanjmen.simplecomics.data;

import android.app.Application;
import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.sanjmen.simplecomics.BuildConfig;
import com.sanjmen.simplecomics.data.api.QueryInterceptor;
import com.sanjmen.simplecomics.data.api.RestApiService;
import com.sanjmen.simplecomics.data.source.ComicsDataSource;
import com.sanjmen.simplecomics.data.source.ComicsRepository;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is used by Dagger to inject the required arguments into the {@link ComicsRepository}.
 */
@Module
public class DataModule {

    @NonNull
    @Singleton
    @Provides
    Cache provideOkHttpCache(@NonNull Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @NonNull
    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return gsonBuilder.create();
    }

    @Provides
    @NonNull
    public QueryInterceptor provideQueryParamsInterceptor() {
        return new QueryInterceptor();
    }

    @NonNull
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(@NonNull Cache cache, @NonNull QueryInterceptor queryInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)

                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(queryInterceptor)
                .cache(cache)
                .build();
    }

    @NonNull
    @Singleton
    @Provides
    @Named("OkHttpImageClient")
    OkHttpClient provideOkHttpImageClient(@NonNull Cache cache) {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)

                .addNetworkInterceptor(new StethoInterceptor())
                .cache(cache)
                .build();
    }

    @NonNull
    @Singleton
    @Provides
    RestApiService provideRestApiService(@NonNull Gson gson, @NonNull OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RestApiService.class);
    }

    @NonNull
    @Singleton
    @Provides
    Picasso providePicasso(@NonNull Application application, @NonNull @Named("OkHttpImageClient") OkHttpClient okHttpClient) {
        return new Picasso.Builder(application)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }

    @NonNull
    @Singleton
    @Provides
    ComicsDataSource provideComicsRepository(@NonNull RestApiService restApiService) {
        return new ComicsRepository(restApiService);
    }
}
