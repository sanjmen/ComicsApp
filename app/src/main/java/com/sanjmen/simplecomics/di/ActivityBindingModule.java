package com.sanjmen.simplecomics.di;

import com.sanjmen.simplecomics.comicdetail.ComicDetailActivity;
import com.sanjmen.simplecomics.comics.ComicsActivity;
import com.sanjmen.simplecomics.comics.ComicsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be AppComponent.
 * The beautiful part about this setup is that you never need to tell AppComponent that it is going
 * to have all these subcomponents nor do you need to tell these subcomponents that AppComponent
 * exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 1 subcomponent for each activity.
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = ComicsModule.class)
    abstract ComicsActivity comicsActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract ComicDetailActivity comicDetailActivity();
}
