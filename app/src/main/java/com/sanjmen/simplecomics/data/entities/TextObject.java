package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TextObject implements Parcelable {

    public static final Parcelable.Creator<TextObject> CREATOR = new Parcelable.Creator<TextObject>() {
        @Override
        public TextObject createFromParcel(Parcel source) {
            return new TextObject(source);
        }

        @Override
        public TextObject[] newArray(int size) {
            return new TextObject[size];
        }
    };
    @SerializedName("type")
    @Expose
    private final String type;
    @SerializedName("language")
    @Expose
    private final String language;
    @SerializedName("text")
    @Expose
    private final String text;

    protected TextObject(Parcel in) {
        this.type = in.readString();
        this.language = in.readString();
        this.text = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.language);
        dest.writeString(this.text);
    }
}
