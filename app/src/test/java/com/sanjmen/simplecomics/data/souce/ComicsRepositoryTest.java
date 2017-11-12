package com.sanjmen.simplecomics.data.source;

import com.sanjmen.simplecomics.data.api.RestApiService;
import com.sanjmen.simplecomics.data.entities.ComicDataWrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link ComicsRepository}
 */
@RunWith(MockitoJUnitRunner.class)
public class ComicsRepositoryTest {

    @Mock
    private ComicDataWrapper comicDataWrapper;

    @Mock
    private RestApiService restApiService;

    private ComicsRepository comicsRepository;

    private TestSubscriber<ComicDataWrapper> testSubscriber;

    @Before
    public void setUpComicsRepository() {
        MockitoAnnotations.initMocks(this);
        comicsRepository = new ComicsRepository(restApiService);
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void getComics_from_api() {
        when(comicsRepository.getComics(25))
                .thenReturn(Flowable.just(comicDataWrapper));
        comicsRepository.getComics(25).subscribe(testSubscriber);
        testSubscriber.assertValue(comicDataWrapper);
    }

}
