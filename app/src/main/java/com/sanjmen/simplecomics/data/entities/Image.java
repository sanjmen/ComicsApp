package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    @SerializedName("path")
    @Expose
    private final String path;
    @SerializedName("extension")
    @Expose
    private final String extension;

    protected Image(Parcel in) {
        this.path = in.readString();
        this.extension = in.readString();
    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.extension);
    }
}
