package com.sanjmen.simplecomics.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("PMD.UnusedPrivateField")
public class Comic implements Parcelable {

    public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel source) {
            return new Comic(source);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };
    @SerializedName("id")
    @Expose
    private final Integer id;
    @SerializedName("digitalId")
    @Expose
    private Integer digitalId;
    @SerializedName("title")
    @Expose
    private final String title;
    @SerializedName("issueNumber")
    @Expose
    private Integer issueNumber;
    @SerializedName("variantDescription")
    @Expose
    private String variantDescription;
    @SerializedName("description")
    @Expose
    private final String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("diamondCode")
    @Expose
    private String diamondCode;
    @SerializedName("ean")
    @Expose
    private String ean;
    @SerializedName("issn")
    @Expose
    private String issn;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("textObjects")
    @Expose
    private List<TextObject> textObjects = null;
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("urls")
    @Expose
    private List<Url> urls = null;
    @SerializedName("series")
    @Expose
    private LinkObject series;
    @SerializedName("variants")
    @Expose
    private List<LinkObject> variants = null;
    @SerializedName("collections")
    @Expose
    private List<LinkObject> collections = null;
    @SerializedName("collectedIssues")
    @Expose
    private List<LinkObject> collectedIssues = null;
    @SerializedName("dates")
    @Expose
    private List<ComicDate> dates = null;
    @SerializedName("prices")
    @Expose
    private List<ComicPrice> prices = null;
    @SerializedName("thumbnail")
    @Expose
    private Image thumbnail;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("creators")
    @Expose
    private ComicExtraInfo creators;
    @SerializedName("characters")
    @Expose
    private ComicExtraInfo characters;
    @SerializedName("stories")
    @Expose
    private ComicExtraInfo stories;
    @SerializedName("events")
    @Expose
    private ComicExtraInfo events;

    public Comic(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    protected Comic(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.digitalId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.issueNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.variantDescription = in.readString();
        this.description = in.readString();
        this.modified = in.readString();
        this.isbn = in.readString();
        this.upc = in.readString();
        this.diamondCode = in.readString();
        this.ean = in.readString();
        this.issn = in.readString();
        this.format = in.readString();
        this.pageCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.textObjects = new ArrayList<TextObject>();
        in.readList(this.textObjects, TextObject.class.getClassLoader());
        this.resourceURI = in.readString();
        this.urls = new ArrayList<Url>();
        in.readList(this.urls, Url.class.getClassLoader());
        this.series = in.readParcelable(LinkObject.class.getClassLoader());
        this.variants = new ArrayList<LinkObject>();
        in.readList(this.variants, LinkObject.class.getClassLoader());
        this.collections = new ArrayList<LinkObject>();
        in.readList(this.collections, LinkObject.class.getClassLoader());
        this.collectedIssues = new ArrayList<LinkObject>();
        in.readList(this.collectedIssues, LinkObject.class.getClassLoader());
        this.dates = new ArrayList<ComicDate>();
        in.readList(this.dates, ComicDate.class.getClassLoader());
        this.prices = new ArrayList<ComicPrice>();
        in.readList(this.prices, ComicPrice.class.getClassLoader());
        this.thumbnail = in.readParcelable(Image.class.getClassLoader());
        this.images = new ArrayList<Image>();
        in.readList(this.images, Image.class.getClassLoader());
        this.creators = in.readParcelable(ComicExtraInfo.class.getClassLoader());
        this.characters = in.readParcelable(ComicExtraInfo.class.getClassLoader());
        this.stories = in.readParcelable(ComicExtraInfo.class.getClassLoader());
        this.events = in.readParcelable(ComicExtraInfo.class.getClassLoader());
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public String getModified() {
        return modified;
    }

    public String getFormat() {
        return format;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public ComicExtraInfo getCreators() {
        return creators;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.digitalId);
        dest.writeString(this.title);
        dest.writeValue(this.issueNumber);
        dest.writeString(this.variantDescription);
        dest.writeString(this.description);
        dest.writeString(this.modified);
        dest.writeString(this.isbn);
        dest.writeString(this.upc);
        dest.writeString(this.diamondCode);
        dest.writeString(this.ean);
        dest.writeString(this.issn);
        dest.writeString(this.format);
        dest.writeValue(this.pageCount);
        dest.writeList(this.textObjects);
        dest.writeString(this.resourceURI);
        dest.writeList(this.urls);
        dest.writeParcelable(this.series, flags);
        dest.writeList(this.variants);
        dest.writeList(this.collections);
        dest.writeList(this.collectedIssues);
        dest.writeList(this.dates);
        dest.writeList(this.prices);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeList(this.images);
        dest.writeParcelable(this.creators, flags);
        dest.writeParcelable(this.characters, flags);
        dest.writeParcelable(this.stories, flags);
        dest.writeParcelable(this.events, flags);
    }
}
