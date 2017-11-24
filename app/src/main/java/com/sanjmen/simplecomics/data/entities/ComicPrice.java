package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComicPrice implements Parcelable {

    public static final Parcelable.Creator<ComicPrice> CREATOR = new Parcelable.Creator<ComicPrice>() {
        @Override
        public ComicPrice createFromParcel(Parcel source) {
            return new ComicPrice(source);
        }

        @Override
        public ComicPrice[] newArray(int size) {
            return new ComicPrice[size];
        }
    };
    @SerializedName("type")
    @Expose
    private final String type;
    @SerializedName("price")
    @Expose
    private final Double price;

    protected ComicPrice(Parcel in) {
        this.type = in.readString();
        this.price = (Double) in.readValue(Double.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeValue(this.price);
    }
}
