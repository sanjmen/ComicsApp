package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Simple class to attach and handle links for any object
 */
public class LinkObject implements Parcelable {

    public static final Parcelable.Creator<LinkObject> CREATOR = new Parcelable.Creator<LinkObject>() {
        @Override
        public LinkObject createFromParcel(Parcel source) {
            return new LinkObject(source);
        }

        @Override
        public LinkObject[] newArray(int size) {
            return new LinkObject[size];
        }
    };
    @SerializedName("resourceURI")
    @Expose
    private final String resourceURI;
    @SerializedName("name")
    @Expose
    private final String name;

    protected LinkObject(Parcel in) {
        this.resourceURI = in.readString();
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceURI);
        dest.writeString(this.name);
    }
}
