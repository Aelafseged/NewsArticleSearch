package com.example.aelaf.newsarticlesearch.front.jsonModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class MultimediaItem implements Parcelable {
    private String type;    // image
    private String subtype;    // xlarge
    private String url;    // images/2017/08/
    private int height;    // 400
    private int width;    // 600
    private int rank;    // 0
    private Legacy legacy;

    public MultimediaItem() {

    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    @Override
    public String toString() {
        return "MultimediaItem{" +
                "type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", url='" + url + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", rank=" + rank +
                ", legacy=" + legacy +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.subtype);
        dest.writeString(this.url);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeInt(this.rank);
        dest.writeParcelable(this.legacy, flags);
    }

    protected MultimediaItem(Parcel in) {
        this.type = in.readString();
        this.subtype = in.readString();
        this.url = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
        this.rank = in.readInt();
        this.legacy = in.readParcelable(Legacy.class.getClassLoader());
    }

    public static final Parcelable.Creator<MultimediaItem> CREATOR = new Parcelable.Creator<MultimediaItem>() {
        @Override
        public MultimediaItem createFromParcel(Parcel source) {
            return new MultimediaItem(source);
        }

        @Override
        public MultimediaItem[] newArray(int size) {
            return new MultimediaItem[size];
        }
    };
}