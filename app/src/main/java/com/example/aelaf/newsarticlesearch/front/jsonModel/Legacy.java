package com.example.aelaf.newsarticlesearch.front.jsonModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class Legacy implements Parcelable {
    private int xlargewidth;    // 600
    private String xlarge;    // images/2017/08/
    private int xlargeheight;    // 400

    public int getXlargewidth() {
        return xlargewidth;
    }

    public void setXlargewidth(int xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    public int getXlargeheight() {
        return xlargeheight;
    }

    public void setXlargeheight(int xlargeheight) {
        this.xlargeheight = xlargeheight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.xlargewidth);
        dest.writeString(this.xlarge);
        dest.writeInt(this.xlargeheight);
    }

    public Legacy() {
    }

    protected Legacy(Parcel in) {
        this.xlargewidth = in.readInt();
        this.xlarge = in.readString();
        this.xlargeheight = in.readInt();
    }

    public static final Parcelable.Creator<Legacy> CREATOR = new Parcelable.Creator<Legacy>() {
        @Override
        public Legacy createFromParcel(Parcel source) {
            return new Legacy(source);
        }

        @Override
        public Legacy[] newArray(int size) {
            return new Legacy[size];
        }
    };
}