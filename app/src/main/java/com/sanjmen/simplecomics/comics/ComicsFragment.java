package com.sanjmen.simplecomics.comics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sanjmen.simplecomics.R;
import com.sanjmen.simplecomics.comicdetail.ComicDetailActivity;
import com.sanjmen.simplecomics.data.entities.Comic;
import com.sanjmen.simplecomics.di.ActivityScoped;
import com.sanjmen.simplecomics.utils.DeviceDimensionsHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * Display a grid of {@link Comic}s ordered by title.
 */
@ActivityScoped
public class ComicsFragment extends DaggerFragment implements
        ComicsContract.View, ComicsAdapter.Listener {

    public static final int SPAN_COUNT = 3;
    @Inject
    ComicsContract.Presenter comicsPresenter;
    @Inject
    Picasso picasso;
    @Inject
    DeviceDimensionsHelper deviceDimensionsHelper;
    @BindView(R.id.comics_loading_ui_progress)
    View comicsLoadingUiProgress;
    @BindView(R.id.comics_loading_error_ui_layout)
    View comicsLoadingErrorUiLayout;
    @BindView(R.id.comics_content_ui_recycler)
    RecyclerView comicsContentUiRecycler;
    private boolean hasNext = false;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private GridLayoutManager gridLayoutManager;
    private ComicsAdapter comicsAdapter;
    @NonNull
    private Unbinder unbinder;

    @Inject
    public ComicsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_comics, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, root);
        comicsContentUiRecycler.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        comicsContentUiRecycler.setLayoutManager(gridLayoutManager);
        comicsAdapter = new ComicsAdapter(this, picasso, deviceDimensionsHelper);
        comicsContentUiRecycler.setAdapter(comicsAdapter);

        comicsContentUiRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    if (hasNext) {
                        comicsPresenter.loadNextPage();
                        hasNext = false;
                        showMessage(getString(R.string.comics_loading_more));
                    }
                }
            }
        });

        comicsPresenter.takeView(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        comicsPresenter.dropView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        comicsPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                comicsAdapter.removeData();
                comicsPresenter.refresh();
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public void showLoadingUi() {
        comicsContentUiRecycler.setVisibility(View.GONE);
        comicsLoadingErrorUiLayout.setVisibility(View.GONE);
        comicsLoadingUiProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContentUi(List<Comic> comics, boolean hasNext) {
        if (hasNext) {
            comicsAdapter.setData(comics);
            comicsContentUiRecycler.setVisibility(View.VISIBLE);
            comicsLoadingErrorUiLayout.setVisibility(View.GONE);
            comicsLoadingUiProgress.setVisibility(View.GONE);
        }

        this.hasNext = hasNext;
    }

    @Override
    public void showErrorUi() {
        showMessage(getString(R.string.comics_loading_error_text));

        comicsContentUiRecycler.setVisibility(View.GONE);
        comicsLoadingErrorUiLayout.setVisibility(View.VISIBLE);
        comicsLoadingUiProgress.setVisibility(View.GONE);
    }

    private void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void showComicDetailsUi(Comic comic) {
        Intent intent = new Intent(getActivity(), ComicDetailActivity.class);
        intent.putExtra(ComicDetailActivity.COMIC, comic);
        startActivity(intent);
    }

    @Override
    public void onComicClick(Comic comic) {
        showComicDetailsUi(comic);
    }
}
