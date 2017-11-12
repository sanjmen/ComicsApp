package com.sanjmen.simplecomics.comics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sanjmen.simplecomics.BuildConfig;
import com.sanjmen.simplecomics.data.source.ComicsRepository;
import com.sanjmen.simplecomics.di.ActivityScoped;
import com.sanjmen.simplecomics.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@ActivityScoped
public class ComicsPresenter implements ComicsContract.Presenter {

    private final ComicsRepository comicsRepository;
    @NonNull
    private final BaseSchedulerProvider schedulerProvider;
    @Nullable
    private ComicsContract.View comicsView;
    @NonNull
    private CompositeDisposable compositeDisposable;

    private int offset;

    @Inject
    ComicsPresenter(@NonNull ComicsRepository comicsRepository,
                    @NonNull BaseSchedulerProvider schedulerProvider) {

        this.comicsRepository = comicsRepository;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void takeView(ComicsContract.View view) {
        this.comicsView = view;
        this.offset = 0;
        loadComics(false);
    }

    @Override
    public void dropView() {
        comicsView = null;

    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void loadNextPage() {
        loadComics(false);
    }

    @Override
    public void refresh() {
        loadComics(true);
    }

    private void loadComics(boolean forceUpdate) {
        compositeDisposable.clear();

        if (offset == 0) {
            if (comicsView != null) {
                comicsView.showLoadingUi();
            }
        }
        if (forceUpdate) {
            offset = 0;
            if (comicsView != null) {
                comicsView.showLoadingUi();
            }
            comicsRepository.refreshComics();
        }

        Disposable disposable = comicsRepository.getComics(offset)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                        comicDataWrapper -> {
                            int total = comicDataWrapper.getData().getTotal();
                            int offset = comicDataWrapper.getData().getOffset();
                            boolean hasNext = (total > offset);
                            if (comicsView != null) {
                                comicsView.showContentUi(comicDataWrapper.getData().getResults(), hasNext);
                            }
                        },
                        error -> {
                            if (comicsView != null) {
                                comicsView.showErrorUi();
                            }
                        }
                );

        offset += BuildConfig.API_RESULTS_LIMIT;

        compositeDisposable.add(disposable);
    }
}
