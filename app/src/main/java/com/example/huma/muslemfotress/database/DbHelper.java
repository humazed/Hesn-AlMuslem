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

    private static final String DATABASE_NAME = "hadeth.db";
    private static final int DATABASE_VERSION = 1;

    public static final String SELECT_QUARRY =
            "SELECT * FROM Category LEFT OUTER JOIN Hadeth on Category._id = Hadeth.categoryID";

    public static final String SELECT_CATEGORY_QUARRY = "SELECT * FROM Category";
    public static final String SELECT_HADETH_QUARRY = "SELECT * FROM Hadeth WHERE categoryID = ?";
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

                categories.add(new Category(getString(categoryCursor, CategoryTable.COL_TITLE_AR),
                        getString(categoryCursor, CategoryTable.COL_TITLE_EN),
                        hadeths));
            } while (categoryCursor.moveToNext());
        }
        categoryCursor.close();

        return categories;
    }

    private String getString(Cursor cursor, String s) {
        return cursor.getString(cursor.getColumnIndex(s));
    }
}
