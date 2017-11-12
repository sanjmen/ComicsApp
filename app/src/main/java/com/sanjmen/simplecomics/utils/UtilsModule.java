package com.sanjmen.simplecomics.utils;

import android.app.Application;
import android.support.annotation.NonNull;

import com.sanjmen.simplecomics.utils.schedulers.BaseSchedulerProvider;
import com.sanjmen.simplecomics.utils.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @NonNull
    @Provides
    DeviceDimensionsHelper provideDeviceDimensionsHelper(@NonNull Application application) {
        return new DeviceDimensionsHelper(application);
    }

    @NonNull
    @Singleton
    @Provides
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}
