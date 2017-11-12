package com.sanjmen.simplecomics.comics;

import com.sanjmen.simplecomics.BasePresenter;
import com.sanjmen.simplecomics.BaseView;
import com.sanjmen.simplecomics.data.entities.Comic;

import java.util.List;

public interface ComicsContract {

    interface View extends BaseView<Presenter> {

        void showLoadingUi();

        void showContentUi(List<Comic> comicList, boolean hasNext);

        void showErrorUi();
    }

    interface Presenter extends BasePresenter<View> {

        void loadNextPage();

        void refresh();

        void takeView(ComicsContract.View view);

        void dropView();

        void unsubscribe();
    }
}
