package com.sanjmen.simplecomics.comicdetail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sanjmen.simplecomics.R;
import com.sanjmen.simplecomics.data.entities.Comic;
import com.sanjmen.simplecomics.data.entities.Summary;
import com.sanjmen.simplecomics.utils.DeviceDimensionsHelper;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

import static android.view.View.GONE;
import static com.sanjmen.simplecomics.utils.ImageSizes.getImageSize;

public class ComicDetailActivity extends DaggerAppCompatActivity {

    public static final String COMIC = "COMIC";
    @Inject
    Picasso picasso;
    @Inject
    DeviceDimensionsHelper deviceDimensionsHelper;
    @BindView(R.id.comic_detail_title_text_view)
    TextView titleTextView;
    @BindView(R.id.comic_detail_thumbnail_image_view)
    ImageView thumbnailImageView;
    @BindView(R.id.comic_detail_loading_ui_progress)
    ProgressBar progressBar;
    @BindView(R.id.comic_detail_description_text_view)
    TextView descriptionTextView;
    @BindView(R.id.comic_detail_issue_number_field_text_view)
    TextView issueNumberFieldTextView;
    @BindView(R.id.comic_detail_issue_number_text_view)
    TextView issueNumberTextView;
    @BindView(R.id.comic_detail_format_field_text_view)
    TextView formatFieldTextView;
    @BindView(R.id.comic_detail_format_text_view)
    TextView formatTextView;
    @BindView(R.id.comic_detail_page_count_field_text_view)
    TextView pageCountFieldTextView;
    @BindView(R.id.comic_detail_page_count_text_view)
    TextView pageCountTextView;
    @BindView(R.id.comic_detail_creators_field_text_view)
    TextView creatorsFieldTextView;
    @BindView(R.id.comic_detail_creators_text_view)
    TextView creatorsTextView;
    private Comic comic;
    @NonNull
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comic_detail);
        unbinder = ButterKnife.bind(this, this);

        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            if (args != null) {
                comic = args.getParcelable(COMIC);
            }
        } else {
            comic = savedInstanceState.getParcelable(COMIC);
        }

        setupDetail();
    }

    private void setupDetail() {

        titleTextView.setText(comic.getTitle());

        try {
            if (comic.getDescription().length() > 400) {
                descriptionTextView.setText(comic.getDescription().substring(0, 400));
            } else {
                descriptionTextView.setText(comic.getDescription());
            }
        } catch (Exception e) {
            descriptionTextView.setVisibility(GONE);
        }

        issueNumberTextView.setText(String.valueOf(comic.getIssueNumber()));
        formatTextView.setText(comic.getFormat());
        pageCountTextView.setText(String.valueOf(comic.getPageCount()));

        StringBuilder creatorsText = new StringBuilder();
        for (Summary creator : comic.getCreators().getItems()) {
            creatorsText.append(creator.getRole().toUpperCase() + ": " + creator.getName() + ".\n");
        }
        if (creatorsText.length() > 0) {
            creatorsTextView.setText(creatorsText);
        } else {
            creatorsFieldTextView.setVisibility(GONE);
            creatorsTextView.setVisibility(GONE);
        }

        String imageSize = getImageSize(deviceDimensionsHelper.getDisplayWidth() / 2);
        String imagePath = comic.getThumbnail().getPath() + "/" + imageSize + "." + comic.getThumbnail().getExtension();

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        picasso.load(imagePath).fit().into(
                thumbnailImageView,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                        Drawable image = getResources().getDrawable(R.drawable.image_not_available);
                        thumbnailImageView.setImageDrawable(image);
                        thumbnailImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
