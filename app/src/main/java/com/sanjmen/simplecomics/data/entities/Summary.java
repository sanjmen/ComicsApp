package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary implements Parcelable {

    public static final Parcelable.Creator<Summary> CREATOR = new Parcelable.Creator<Summary>() {
        @Override
        public Summary createFromParcel(Parcel source) {
            return new Summary(source);
        }

        @Override
        public Summary[] newArray(int size) {
            return new Summary[size];
        }
    };
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    @Nullable
    private String role;
    @SerializedName("type")
    @Expose
    @Nullable
    private String type;

    public Summary() {
    }

    protected Summary(Parcel in) {
        this.resourceURI = in.readString();
        this.name = in.readString();
        this.role = in.readString();
        this.type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceURI);
        dest.writeString(this.name);
        dest.writeString(this.role);
        dest.writeString(this.type);
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public String getRole() {
        return role;
    }

    @Nullable
    public String getType() {
        return type;
    }
}
