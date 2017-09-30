package com.example.aelaf.newsarticlesearch.front.jsonModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class Byline implements Parcelable {
    private String original;    // ByERICLIPTONand

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original);
    }

    public Byline() {
    }

    protected Byline(Parcel in) {
        this.original = in.readString();
    }

    public static final Parcelable.Creator<Byline> CREATOR = new Parcelable.Creator<Byline>() {
        @Override
        public Byline createFromParcel(Parcel source) {
            return new Byline(source);
        }

        @Override
        public Byline[] newArray(int size) {
            return new Byline[size];
        }
    };
}