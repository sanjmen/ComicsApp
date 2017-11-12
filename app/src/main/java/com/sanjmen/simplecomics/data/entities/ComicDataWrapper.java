package com.sanjmen.simplecomics.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComicDataWrapper {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("status")
    @Expose
    private String status;

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
    private ComicDataContainer data;

    public ComicDataWrapper(Integer code, String status, ComicDataContainer data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public ComicDataContainer getData() {
        return data;
    }
}
