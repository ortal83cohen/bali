package apps.cohen.bali.activities;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import apps.cohen.bali.App;
import apps.cohen.bali.R;
import apps.cohen.bali.fragments.FragmentDrawer;
import apps.cohen.bali.fragments.ItemListsFragment;
import apps.cohen.bali.fragments.NewItemFragment;
import apps.cohen.bali.fragments.PopularItemsFragment;
import apps.cohen.bali.views.SlidingTabLayout;


public class ActivityMain extends ActionBarActivity {//implements  View.OnClickListener {

    //int representing our 0th tab corresponding to the Fragment where search results are dispalyed
    public static final int TAB_SEARCH_RESULTS = 0;

    //int corresponding to our 1st tab corresponding to the Fragment where box office hits are dispalyed
    public static final int TAB_HITS = 1;

    //int corresponding to our 2nd tab corresponding to the Fragment where upcoming movies are displayed
    public static final int TAB_UPCOMING = 2;

    //int corresponding to the number of tabs in our Activity
    public static final int TAB_COUNT = 3;
    //int corresponding to the id of our JobSchedulerService

    private Toolbar mToolbar;

    //a layout grouping the toolbar and the tabs together
    private ViewGroup mContainerToolbar;

    private ViewPager mPager;

    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawer();
        setupTabs();
        //animate the Toolbar when it comes into the picture
//        AnimationUtils.animateToolbarDroppingDown(mContainerToolbar);
        Picasso.with(this).setLoggingEnabled(true);
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        App.provide(this).facebook().pause(this);
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
////        getNavigationDrawer().setCurrentTab(NavigationDrawer.TAB_LOCATION_CHOOSER);
//
//        App.provide(this).facebook().resume(this);
//    }
    private void setupTabs() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        //make sure all tabs take the full horizontal screen space and divide it equally amongst themselves
        mTabs.setDistributeEvenly(true);
        mTabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //color of the tab indicator
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mTabs.setViewPager(mPager);
    }

    private void setupDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mContainerToolbar = (ViewGroup) findViewById(R.id.container_app_bar);
        //set the Toolbar as ActionBar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //setup the NavigationDrawer
        FragmentDrawer drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }

    public void onDrawerItemClicked(int index) {
        Toast.makeText(this, String.valueOf(index), Toast.LENGTH_SHORT).show();
    }

    public View getContainerToolbar() {
        return mContainerToolbar;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        private String[] tabText = getResources().getStringArray(R.array.tabs);

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
//            tabText = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = PopularItemsFragment.getInstance();
                    break;
                case 1:
                    fragment = ItemListsFragment.newInstance();
                    break;
                case 2:
                    fragment = NewItemFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabText[position];

        }

        @Override
        public int getCount() {
            return tabText.length;
        }
    }


}
