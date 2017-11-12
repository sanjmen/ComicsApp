package com.sanjmen.simplecomics.comics;

import com.sanjmen.simplecomics.data.entities.Comic;
import com.sanjmen.simplecomics.data.entities.ComicDataContainer;
import com.sanjmen.simplecomics.data.entities.ComicDataWrapper;
import com.sanjmen.simplecomics.data.source.ComicsRepository;
import com.sanjmen.simplecomics.utils.schedulers.BaseSchedulerProvider;
import com.sanjmen.simplecomics.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link ComicsPresenter}
 */
@RunWith(MockitoJUnitRunner.class)
public class ComicsPresenterTest {

    private static ComicDataWrapper COMIC_WRAPPER;
    private static List<Comic> COMIC_LIST;
    @Mock
    private ComicsRepository comicsRepository;
    @Mock
    private ComicsContract.View comicsView;
    private BaseSchedulerProvider mSchedulerProvider;
    private ComicsPresenter comicsPresenter;

    @Before
    public void setupComicsPresenter() {
        MockitoAnnotations.initMocks(this);

        COMIC_LIST = new ArrayList<>();
        Comic comic = new Comic(1, "comic number one", "this is the comic number one");
        COMIC_LIST.add(comic);
        ComicDataContainer comicDataContainer = new ComicDataContainer(1, 0, COMIC_LIST);
        COMIC_WRAPPER = new ComicDataWrapper(200, "OK", comicDataContainer);

        mSchedulerProvider = new ImmediateSchedulerProvider();
        when(comicsRepository.getComics(0)).thenReturn(Flowable.just(COMIC_WRAPPER));

        comicsPresenter = new ComicsPresenter(comicsRepository, mSchedulerProvider);
    }

    @Test
    public void presenterTakesViewAndShowLoadingUi() {
        comicsPresenter.takeView(comicsView);
        InOrder inOrder = Mockito.inOrder(comicsView);
        inOrder.verify(comicsView, times(1)).showLoadingUi();
        inOrder.verify(comicsView, times(1)).showContentUi(COMIC_LIST, true);
    }

    @Test
    public void presenterForcedUpdateRepositoryRefreshComics() {
        comicsPresenter.refresh();
        verify(comicsRepository).refreshComics();
    }


    @Test
    public void fetchValidDataShouldLoadIntoView() {
        comicsPresenter.takeView(comicsView);
        InOrder inOrder = Mockito.inOrder(comicsView);
        inOrder.verify(comicsView, times(1)).showLoadingUi();
        verify(comicsView).showLoadingUi();
        verify(comicsRepository).getComics(0);
        verify(comicsView).showContentUi(COMIC_LIST, true);
    }
}
