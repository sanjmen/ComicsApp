package com.sanjmen.simplecomics.comics;

import com.sanjmen.simplecomics.di.ActivityScoped;
import com.sanjmen.simplecomics.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link ComicsPresenter}.
 */
@Module
public abstract class ComicsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ComicsFragment comicsFragment();

    @ActivityScoped
    @Binds
    abstract ComicsContract.Presenter comicsPresenter(ComicsPresenter presenter);
}
