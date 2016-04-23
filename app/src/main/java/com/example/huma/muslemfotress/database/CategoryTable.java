package com.example.huma.muslemfotress.database;

import android.provider.BaseColumns;

/**
 * Created by 3aSsoMa on 4/13/2016.
 */
public interface CategoryTable extends BaseColumns{

    String COL_TITLE_AR = "titleAr";
    String COL_TITLE_EN = "titleEn";
    String COL_FAV = "fav";
    String[] projection = {_ID, COL_TITLE_AR,COL_TITLE_EN};





}
