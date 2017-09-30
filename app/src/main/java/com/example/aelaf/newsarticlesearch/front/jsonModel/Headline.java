package com.example.aelaf.newsarticlesearch.front.jsonModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class Headline implements Parcelable{
    private String main;    // UnderTrump,Coal
    private String kicker;    // TrumpRules
    private String printHeadline;    // TrumpUnravelsCo

    public Headline() {
    }

    private Headline(Parcel in) {
        main = in.readString();
        kicker = in.readString();
        printHeadline = in.readString();
    }

    public static final Parcelable.Creator<Headline> CREATOR = new Parcelable.Creator<Headline>() {
        @Override
        public Headline createFromParcel(Parcel in) {
            return new Headline(in);
        }

        @Override
        public Headline[] newArray(int size) {
            return new Headline[size];
        }
    };

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    @Override
    public String toString() {
        return "Headline{" +
                "main='" + main + '\'' +
                ", kicker='" + kicker + '\'' +
                ", printHeadline='" + printHeadline + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(main);
        parcel.writeString(kicker);
        parcel.writeString(printHeadline);
    }
}