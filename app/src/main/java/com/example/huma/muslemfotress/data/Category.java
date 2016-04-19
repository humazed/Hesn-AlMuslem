package com.example.huma.muslemfotress.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import lombok.Data;

/**
 * User: huma
 * Date: 4/3/2016
 */
@Data
public class Category implements Parcelable {
    private String titleAr;
    private String titleEn;
    private ArrayList<Hadeth> mHadeths = new ArrayList<>();

    public Category(String titleAr, String titleEn, ArrayList<Hadeth> hadeths) {
        this.titleAr = titleAr;
        this.titleEn = titleEn;
        mHadeths = hadeths;
    }

    protected Category(Parcel in) {
        titleAr = in.readString();
        mHadeths = in.createTypedArrayList(Hadeth.CREATOR);
    }

    public ArrayList<Hadeth> getHadeths() {
        return mHadeths;
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
        dest.writeString(titleAr);
        dest.writeTypedList(mHadeths);
    }

}
