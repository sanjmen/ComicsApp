package com.sanjmen.simplecomics.comics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.sanjmen.simplecomics.R;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class ComicsActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<ComicsFragment> comicFragmentProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comics);

        setupToolbar();

        ComicsFragment comicsFragment =
                (ComicsFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (comicsFragment == null) {
            comicsFragment = comicFragmentProvider.get();


            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content_frame, comicsFragment);
            transaction.commit();
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
