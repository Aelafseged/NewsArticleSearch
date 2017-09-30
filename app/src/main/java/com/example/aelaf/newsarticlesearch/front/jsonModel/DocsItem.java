package com.example.aelaf.newsarticlesearch.front.jsonModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: aelaf
 * Created by: ModelGenerator on 9/28/17
 */
public class DocsItem implements Parcelable {
    private String web_url;    // https://www.nyt
    private String snippet;    // Abusiness_frien
    private String printPage;    // 1
    private Blog blog;
    private String source;    // TheNewYorkTimes
    private List<MultimediaItem> multimedia;
    private Headline headline;
    private List<KeywordsItem> keywords;
    private String pubDate;    // 2017_08_06T15:0
    private String documentType;    // article
    private String newDesk;    // National
    private String sectionName;    // Politics
    private Byline byline;
    @SerializedName("type_of_material")
    private String typeOfMaterial;    // News
    private String Id;    // 598730c47c459f2
    private int wordCount;    // 2771
    private double score;    // 5.911105
    private String uri;    // nyt://article/f

    public DocsItem(){
        multimedia = new ArrayList<>();
        keywords = new ArrayList<>();
    }





    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPrintPage() {
        return printPage;
    }

    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<MultimediaItem> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MultimediaItem> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<KeywordsItem> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordsItem> keywords) {
        this.keywords = keywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNewDesk() {
        return newDesk;
    }

    public void setNewDesk(String newDesk) {
        this.newDesk = newDesk;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }



    @Override
    public String toString() {
        return "DocsItem{" +
                "web_url='" + web_url + '\'' +
                ", snippet='" + snippet + '\'' +
                ", printPage='" + printPage + '\'' +
                ", blog=" + blog +
                ", source='" + source + '\'' +
                ", multimedia=" + multimedia +
                ", headline=" + headline +
                ", keywords=" + keywords +
                ", pubDate='" + pubDate + '\'' +
                ", documentType='" + documentType + '\'' +
                ", newDesk='" + newDesk + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", byline=" + byline +
                ", typeOfMaterial='" + typeOfMaterial + '\'' +
                ", Id='" + Id + '\'' +
                ", wordCount=" + wordCount +
                ", score=" + score +
                ", uri='" + uri + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.web_url);
        dest.writeString(this.snippet);
        dest.writeString(this.printPage);
        dest.writeParcelable(this.blog, flags);
        dest.writeString(this.source);
        dest.writeTypedList(this.multimedia);
        dest.writeParcelable(this.headline, flags);
        dest.writeList(this.keywords);
        dest.writeString(this.pubDate);
        dest.writeString(this.documentType);
        dest.writeString(this.newDesk);
        dest.writeString(this.sectionName);
        dest.writeParcelable(this.byline, flags);
        dest.writeString(this.typeOfMaterial);
        dest.writeString(this.Id);
        dest.writeInt(this.wordCount);
        dest.writeDouble(this.score);
        dest.writeString(this.uri);
    }

    protected DocsItem(Parcel in) {
        this.web_url = in.readString();
        this.snippet = in.readString();
        this.printPage = in.readString();
        this.blog = in.readParcelable(Blog.class.getClassLoader());
        this.source = in.readString();
        this.multimedia = in.createTypedArrayList(MultimediaItem.CREATOR);
        this.headline = in.readParcelable(Headline.class.getClassLoader());
        this.keywords = new ArrayList<>();
        in.readList(this.keywords, KeywordsItem.class.getClassLoader());
        this.pubDate = in.readString();
        this.documentType = in.readString();
        this.newDesk = in.readString();
        this.sectionName = in.readString();
        this.byline = in.readParcelable(Byline.class.getClassLoader());
        this.typeOfMaterial = in.readString();
        this.Id = in.readString();
        this.wordCount = in.readInt();
        this.score = in.readDouble();
        this.uri = in.readString();
    }

    public static final Parcelable.Creator<DocsItem> CREATOR = new Parcelable.Creator<DocsItem>() {
        @Override
        public DocsItem createFromParcel(Parcel source) {
            return new DocsItem(source);
        }

        @Override
        public DocsItem[] newArray(int size) {
            return new DocsItem[size];
        }
    };
}