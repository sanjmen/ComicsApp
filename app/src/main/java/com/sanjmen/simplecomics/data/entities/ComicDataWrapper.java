package com.sanjmen.simplecomics.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("PMD.UnusedPrivateField")
public class ComicDataWrapper {

    @SerializedName("code")
    @Expose
    private final Integer code;

    @SuppressWarnings("PMD.SingularField")
    @SerializedName("status")
    @Expose
    private final String status;

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("attributionText")
    @Expose
    private String attributionText;

    @SerializedName("attributionHTML")
    @Expose
    private String attributionHTML;

    @SerializedName("etag")
    @Expose
    private String etag;

    @SerializedName("data")
    @Expose
    private final ComicDataContainer data;

    public ComicDataWrapper(Integer code, String status, ComicDataContainer data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ComicDataContainer getData() {
        return data;
    }
}
