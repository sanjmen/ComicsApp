package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ComicExtraInfo implements Parcelable {

    public static final Parcelable.Creator<ComicExtraInfo> CREATOR = new Parcelable.Creator<ComicExtraInfo>() {
        @Override
        public ComicExtraInfo createFromParcel(Parcel source) {
            return new ComicExtraInfo(source);
        }

        @Override
        public ComicExtraInfo[] newArray(int size) {
            return new ComicExtraInfo[size];
        }
    };
    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<Summary> items = null;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    public ComicExtraInfo() {
    }

    protected ComicExtraInfo(Parcel in) {
        this.available = (Integer) in.readValue(Integer.class.getClassLoader());
        this.collectionURI = in.readString();
        this.items = new ArrayList<Summary>();
        in.readList(this.items, Summary.class.getClassLoader());
        this.returned = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.available);
        dest.writeString(this.collectionURI);
        dest.writeList(this.items);
        dest.writeValue(this.returned);
    }

    public List<Summary> getItems() {
        return items;
    }
}
