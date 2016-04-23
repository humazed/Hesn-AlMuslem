package com.example.huma.muslemfotress.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huma.muslemfotress.R;
import com.example.huma.muslemfotress.data.Category;
import com.example.huma.muslemfotress.data.Hadeth;
import com.example.huma.muslemfotress.database.DbHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {
    private static final String TAG = ViewPagerActivity.class.getSimpleName();

    @Bind(R.id.fav_button) ImageView mFavButton;

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    Category mCategory;
    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDbHelper = new DbHelper(this);

        mCategory = getIntent().getParcelableExtra(MainListActivity.CATEGORY);
        Log.d(TAG, "onCreate " + "Category: " + mCategory);
        if (MainListActivity.isEnglish)
            getSupportActionBar().setTitle(mCategory.getTitleEn());
        else getSupportActionBar().setTitle(mCategory.getTitleAr());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        if (mViewPager != null) mViewPager.setAdapter(mSectionsPagerAdapter);

        mFavButton.setSelected(mDbHelper.isFav(mCategory.getId()));

        mFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mFavButton.isSelected()) {
                    mFavButton.setSelected(true);
                    mDbHelper.addFav(mCategory.getId());
                } else {
                    mFavButton.setSelected(false);
                    mDbHelper.removeFav(mCategory.getId());
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_HADETH = "hadeth";
        @Bind(R.id.body_textView) TextView mBodyTextView;
        @Bind(R.id.info_textView) TextView mInfoTextView;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(Hadeth hadeth) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putParcelable(ARG_HADETH, hadeth);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_view_pager, container, false);
            ButterKnife.bind(this, rootView);

            refresh();

            return rootView;
        }

        private void refresh() {
            if (getArguments().getParcelable(ARG_HADETH) != null) {
                if (MainListActivity.isEnglish) {
                    mBodyTextView.setText(((Hadeth) getArguments().getParcelable(ARG_HADETH)).getBodyEn());
                    mInfoTextView.setText(((Hadeth) getArguments().getParcelable(ARG_HADETH)).getInfoEn());
                } else {
                    mBodyTextView.setText(((Hadeth) getArguments().getParcelable(ARG_HADETH)).getItem1());
                    mInfoTextView.setText(((Hadeth) getArguments().getParcelable(ARG_HADETH)).getItem2());
                }
            }
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(mCategory.getHadeths().get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return mCategory.getHadeths().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
