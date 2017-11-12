package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComicDate implements Parcelable {

    public static final Parcelable.Creator<ComicDate> CREATOR = new Parcelable.Creator<ComicDate>() {
        @Override
        public ComicDate createFromParcel(Parcel source) {
            return new ComicDate(source);
        }

        @Override
        public ComicDate[] newArray(int size) {
            return new ComicDate[size];
        }
    };
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;

    public ComicDate() {
    }

    protected ComicDate(Parcel in) {
        this.type = in.readString();
        this.date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.date);
    }
}
