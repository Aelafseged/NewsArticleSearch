package com.example.aelaf.newsarticlesearch.front.jsonModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class KeywordsItem implements Parcelable {
    private String isMajor;    // N
    private int rank;    // 1
    private String name;    // subject
    private String value;    // MinesandMining

    public String getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.isMajor);
        dest.writeInt(this.rank);
        dest.writeString(this.name);
        dest.writeString(this.value);
    }

    public KeywordsItem() {
    }

    protected KeywordsItem(Parcel in) {
        this.isMajor = in.readString();
        this.rank = in.readInt();
        this.name = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<KeywordsItem> CREATOR = new Parcelable.Creator<KeywordsItem>() {
        @Override
        public KeywordsItem createFromParcel(Parcel source) {
            return new KeywordsItem(source);
        }

        @Override
        public KeywordsItem[] newArray(int size) {
            return new KeywordsItem[size];
        }
    };
}