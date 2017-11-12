package com.sanjmen.simplecomics.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import javax.inject.Inject;

public class DeviceDimensionsHelper {

    public final DisplayMetrics displayMetrics;
    @NonNull
    private final Context context;

    @Inject
    public DeviceDimensionsHelper(Context context) {
        this.context = context;
        this.displayMetrics = context.getResources().getDisplayMetrics();
    }

    /**
     * return display width in pixels.
     */
    public int getDisplayWidth() {
        return displayMetrics.widthPixels;
    }


    /**
     * return display height in pixels.
     */
    public int getDisplayHeight() {
        return displayMetrics.heightPixels;
    }

    /**
     * Convert dp to pixel
     *
     * @param dp
     * @return
     */
    public float convertDpToPixel(float dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    /**
     * Convert pixel to dp
     *
     * @param px
     * @return
     */
    public float convertPixelsToDp(float px) {
        Resources r = context.getResources();
        DisplayMetrics metrics = r.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT); // density default 160
        return dp;
    }
}
