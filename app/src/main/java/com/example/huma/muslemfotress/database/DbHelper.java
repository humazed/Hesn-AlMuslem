package com.example.huma.muslemfotress.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.huma.muslemfotress.data.Category;
import com.example.huma.muslemfotress.data.Hadeth;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * User: huma
 * Date: 4/13/2016
 */
public class DbHelper extends SQLiteAssetHelper {
    private static final String TAG = DbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "hadeth.db";
    private static final int DATABASE_VERSION = 1;

    public static final String SELECT_QUARRY =
            "SELECT * FROM Category LEFT OUTER JOIN Hadeth on Category._id = Hadeth.categoryID";

    public static final String SELECT_CATEGORY_QUARRY = "SELECT * FROM Category";
    public static final String SELECT_HADETH_QUARRY = "SELECT * FROM Hadeth WHERE categoryID = ?";

    public static final String ADD_FAV_QUARRY = "UPDATE Category SET fav=1 WHERE _id=?;";
    public static final String REMOVE_FAV_QUARRY = "UPDATE Category SET fav=0 WHERE _id=?;";
    public static final String SELECT_FAV_QUARRY = "SELECT * FROM Category WHERE _id=? AND fav=1;";
    public static final String SELECT_FAVS_QUARRY = "SELECT * FROM Category WHERE fav=1;";

    private final SQLiteDatabase dp;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
        dp = getReadableDatabase();
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        Cursor categoryCursor = dp.rawQuery(SELECT_CATEGORY_QUARRY, null);
        if (categoryCursor.moveToFirst()) {
            do {
                int categoryID = categoryCursor.getInt(categoryCursor.getColumnIndex(CategoryTable._ID));
                Cursor hadethCursor = dp.rawQuery(SELECT_HADETH_QUARRY, new String[]{String.valueOf(categoryID)});
                ArrayList<Hadeth> hadeths = new ArrayList<>();
                if (hadethCursor.moveToFirst()) {
                    do {
                        hadeths.add(new Hadeth(getString(hadethCursor, HadethTable.COL_BODY_AR),
                                getString(hadethCursor, HadethTable.COL_INFO_AR),
                                getString(hadethCursor, HadethTable.COl_BODY_EN),
                                getString(hadethCursor, HadethTable.COL_INFO_EN)));
                    } while (hadethCursor.moveToNext());
                }
                hadethCursor.close();

                categories.add(new Category(
                        getInt(categoryCursor, CategoryTable._ID),
                        getString(categoryCursor, CategoryTable.COL_TITLE_AR),
                        getString(categoryCursor, CategoryTable.COL_TITLE_EN),
                        isFav(categoryID),
                        hadeths));
            } while (categoryCursor.moveToNext());
        }
        categoryCursor.close();

        return categories;
    }

    public void addFav(int id) {
        dp.execSQL(ADD_FAV_QUARRY, new String[]{String.valueOf(id)});
    }

    public void removeFav(int id) {
        dp.execSQL(REMOVE_FAV_QUARRY, new String[]{String.valueOf(id)});
    }

    public boolean isFav(int id) {
        Cursor cursor = dp.rawQuery(SELECT_FAV_QUARRY, new String[]{String.valueOf(id)});

        boolean isFav = cursor.getCount() > 0;
        cursor.close();
        return isFav;
    }

    public ArrayList<Category> getFavourites() {
        ArrayList<Category> categories = new ArrayList<>();
        Cursor categoryCursor = dp.rawQuery(SELECT_FAVS_QUARRY, null);
        if (categoryCursor.moveToFirst()) {
            do {
                int categoryID = categoryCursor.getInt(categoryCursor.getColumnIndex(CategoryTable._ID));
                Cursor hadethCursor = dp.rawQuery(SELECT_HADETH_QUARRY, new String[]{String.valueOf(categoryID)});
                ArrayList<Hadeth> hadeths = new ArrayList<>();
                if (hadethCursor.moveToFirst()) {
                    do {
                        hadeths.add(new Hadeth(getString(hadethCursor, HadethTable.COL_BODY_AR),
                                getString(hadethCursor, HadethTable.COL_INFO_AR),
                                getString(hadethCursor, HadethTable.COl_BODY_EN),
                                getString(hadethCursor, HadethTable.COL_INFO_EN)));
                    } while (hadethCursor.moveToNext());
                }
                hadethCursor.close();

                categories.add(new Category(
                        getInt(categoryCursor, CategoryTable._ID),
                        getString(categoryCursor, CategoryTable.COL_TITLE_AR),
                        getString(categoryCursor, CategoryTable.COL_TITLE_EN),
                        isFav(categoryID),
                        hadeths));
            } while (categoryCursor.moveToNext());
        }
        categoryCursor.close();

        return categories;
    }

    private String getString(Cursor cursor, String s) {
        return cursor.getString(cursor.getColumnIndex(s));
    }

    private int getInt(Cursor cursor, String s) {
        return cursor.getInt(cursor.getColumnIndex(s));
    }
}
