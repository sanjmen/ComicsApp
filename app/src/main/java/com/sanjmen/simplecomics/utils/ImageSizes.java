package com.sanjmen.simplecomics.utils;

public class ImageSizes {

    /**
     * portrait_small 	    50x75px
     * portrait_medium 	    100x150px
     * portrait_xlarge 	    150x225px
     * portrait_fantastic 	168x252px
     * portrait_uncanny 	300x450px
     * portrait_incredible 	216x324px
     */


    public static String getImageSize(int width) {
        if (width <= 50) {
            return "portrait_small";
        }
        if (width > 50 && width <= 100) {
            return "portrait_medium";
        }
        if (width > 100 && width <= 150) {
            return "portrait_xlarge";
        }
        if (width > 150 && width <= 168) {
            return "portrait_fantastic";
        }
        if (width > 168 && width <= 216) {
            return "portrait_incredible";
        }
        if (width > 216) {
            return "portrait_uncanny";
        }

        return "portrait_small";
    }
}
