package com.example.huma.muslemfotress.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.huma.muslemfotress.R;
import com.example.huma.muslemfotress.data.Category;
import com.example.huma.muslemfotress.database.DbHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavActivity extends AppCompatActivity {
    private static final String TAG = FavActivity.class.getSimpleName();
    @Bind(R.id.listView) ListView mListView;

    private ArrayList<Category> mFavouriteCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        ButterKnife.bind(this);

        DbHelper dbHelper = new DbHelper(this);

        mFavouriteCategories = dbHelper.getFavourites();

        refresh(mFavouriteCategories);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavActivity.this, ViewPagerActivity.class);
                intent.putExtra(MainListActivity.CATEGORY, mFavouriteCategories.get(position));
                startActivity(intent);
            }
        });
    }

    private void refresh(ArrayList<Category> categories) {
        ArrayList<String> titles = new ArrayList<>();
        if (MainListActivity.isEnglish) {
            for (Category category : categories)
                titles.add(category.getTitleEn());
        } else {
            for (Category category : categories)
                titles.add(category.getTitleAr());
        }

        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles));
    }
}
