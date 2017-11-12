package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicDataContainer implements Parcelable {

    public static final Parcelable.Creator<ComicDataContainer> CREATOR = new Parcelable.Creator<ComicDataContainer>() {
        @Override
        public ComicDataContainer createFromParcel(Parcel source) {
            return new ComicDataContainer(source);
        }

        @Override
        public ComicDataContainer[] newArray(int size) {
            return new ComicDataContainer[size];
        }
    };
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("results")
    @Expose
    private List<Comic> results = null;

    public ComicDataContainer(Integer total, Integer offset, List<Comic> results) {
        this.total = total;
        this.offset = offset;
        this.results = results;
    }

    protected ComicDataContainer(Parcel in) {
        this.offset = (Integer) in.readValue(Integer.class.getClassLoader());
        this.limit = (Integer) in.readValue(Integer.class.getClassLoader());
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.results = in.createTypedArrayList(Comic.CREATOR);
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getCount() {
        return count;
    }

    public List<Comic> getResults() {
        return results;
    }

    public void setResults(List<Comic> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.offset);
        dest.writeValue(this.limit);
        dest.writeValue(this.total);
        dest.writeValue(this.count);
        dest.writeTypedList(this.results);
    }
}
