package com.example.huma.muslemfotress.database;

import android.provider.BaseColumns;

/**
 * Created by 3aSsoMa on 4/13/2016.
 */
public interface HadethTable extends BaseColumns {

    String COL_BODY_AR = "bodyAr";
    String COL_INFO_AR = "infoAr";
    String COl_BODY_EN = "bodyEn";
    String COL_INFO_EN = "infoEn";
    String COL_FAV = "fav";
    String COL_CATEGORY_ID = "categoryID";

    String[] PROJECTION = {_ID, COL_BODY_AR, COL_INFO_AR, COl_BODY_EN, COL_INFO_EN, COL_FAV, COL_CATEGORY_ID};

}
