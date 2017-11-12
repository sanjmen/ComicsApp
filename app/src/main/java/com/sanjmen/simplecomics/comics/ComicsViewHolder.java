package com.sanjmen.simplecomics.comics;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sanjmen.simplecomics.R;
import com.sanjmen.simplecomics.data.entities.Comic;
import com.sanjmen.simplecomics.utils.DeviceDimensionsHelper;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sanjmen.simplecomics.utils.ImageSizes.getImageSize;


public class ComicsViewHolder extends RecyclerView.ViewHolder {

    final View rootView;
    private final Picasso picasso;
    private final DeviceDimensionsHelper deviceDimensionsHelper;
    @BindView(R.id.comic_thumbnail_loading_ui_progress)
    ProgressBar progressBar;

    @BindView(R.id.comic_thumbnail_image_view)
    ImageView thumbnailImageView;

    @BindView(R.id.comic_title_text_view)
    TextView titleTextView;


    public ComicsViewHolder(@NonNull View itemView, @NonNull Picasso picasso,
                            @NonNull DeviceDimensionsHelper deviceDimensionsHelper) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.rootView = itemView;
        this.picasso = picasso;
        this.deviceDimensionsHelper = deviceDimensionsHelper;
    }

    public void bind(@NonNull Comic comic) {

        titleTextView.setText(comic.getTitle());

        int deviceWidth = deviceDimensionsHelper.getDisplayWidth();

        int imageWidth = deviceWidth / ComicsFragment.SPAN_COUNT;

        String imageSize = getImageSize(imageWidth);
        String imagePath = comic.getThumbnail().getPath() + "/" + imageSize + "." + comic.getThumbnail().getExtension();

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        picasso.load(imagePath).fit().into(
                thumbnailImageView,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                        Drawable image = rootView.getResources().getDrawable(R.drawable.image_not_available);
                        thumbnailImageView.setImageDrawable(image);
                        thumbnailImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                });

    }
}
