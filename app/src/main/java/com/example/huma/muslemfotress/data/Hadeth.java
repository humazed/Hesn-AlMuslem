package com.example.huma.muslemfotress.data;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

/**
 * User: huma
 * Date: 4/3/2016
 */
@Data
public class Hadeth implements Parcelable {
    private String item1;
    private String item2;
    private String bodyEn;
    private String infoEn;

    public Hadeth(String item1, String item2, String bodyEn, String infoEn) {
        this.item1 = item1;
        this.item2 = item2;
        this.bodyEn = bodyEn;
        this.infoEn = infoEn;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.item1);
        dest.writeString(this.item2);
        dest.writeString(this.bodyEn);
        dest.writeString(this.infoEn);
    }

    protected Hadeth(Parcel in) {
        this.item1 = in.readString();
        this.item2 = in.readString();
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
