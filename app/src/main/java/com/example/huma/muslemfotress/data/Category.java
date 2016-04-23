package com.example.huma.muslemfotress.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User: huma
 * Date: 4/3/2016
 */
@Data
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(suppressConstructorProperties = true)
public class Category implements Parcelable {
    private int id;
    private String titleAr;
    private String titleEn;
    private boolean fav;
    private ArrayList<Hadeth> hadeths = new ArrayList<>();


    protected Category(Parcel in) {
        id = in.readInt();
        titleAr = in.readString();
        titleEn = in.readString();
        fav = in.readByte() != 0;
        hadeths = in.createTypedArrayList(Hadeth.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titleAr);
        dest.writeString(titleEn);
        dest.writeByte((byte) (fav ? 1 : 0));
        dest.writeTypedList(hadeths);
    }
}
