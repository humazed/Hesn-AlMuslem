package com.example.huma.muslemfotress.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: huma
 * Date: 4/3/2016
 */
public class Hadeth implements Parcelable {
    private String bodyAr;
    private String infoAr;
    private String bodyEn;
    private String infoEn;

    public Hadeth(String bodyAr, String infoAr, String bodyEn, String infoEn) {
        this.bodyAr = bodyAr;
        this.infoAr = infoAr;
        this.bodyEn = bodyEn;
        this.infoEn = infoEn;
    }

    public String getInfoEn() {
        return infoEn;
    }

    public void setInfoEn(String infoEn) {
        this.infoEn = infoEn;
    }

    public String getBodyEn() {
        return bodyEn;
    }

    public void setBodyEn(String bodyEn) {
        this.bodyEn = bodyEn;
    }


    public String getBodyAr() {
        return bodyAr;
    }

    public void setBodyAr(String bodyAr) {
        this.bodyAr = bodyAr;
    }

    public String getInfoAr() {
        return infoAr;
    }

    public void setInfoAr(String infoAr) {
        this.infoAr = infoAr;
    }


    @Override
    public String toString() {
        return "Hadeth{" +
                "bodyAr='" + bodyAr + '\'' +
                ", infoAr='" + infoAr + '\'' +
                ", bodyEn='" + bodyEn + '\'' +
                ", infoEn='" + infoEn + '\'' +
                '}';
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bodyAr);
        dest.writeString(this.infoAr);
        dest.writeString(this.bodyEn);
        dest.writeString(this.infoEn);
    }

    protected Hadeth(Parcel in) {
        this.bodyAr = in.readString();
        this.infoAr = in.readString();
        this.bodyEn = in.readString();
        this.infoEn = in.readString();
    }

    public static final Creator<Hadeth> CREATOR = new Creator<Hadeth>() {
        @Override
        public Hadeth createFromParcel(Parcel source) {return new Hadeth(source);}

        @Override
        public Hadeth[] newArray(int size) {return new Hadeth[size];}
    };
}
