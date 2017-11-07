package com.sanjmen.simplecomics;

import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class ComicApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
            Stetho.initializeWithDefaults(this);
        }
    }
}
