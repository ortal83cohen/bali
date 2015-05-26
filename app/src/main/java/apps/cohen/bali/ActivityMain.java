package apps.cohen.bali;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import apps.cohen.bali.anim.AnimationUtils;
import apps.cohen.bali.fragments.FragmentDrawer;



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
    private static final int JOB_ID = 100;
    //tag associated with the FAB menu button that sorts by name
    private static final String TAG_SORT_NAME = "sortName";
    //tag associated with the FAB menu button that sorts by date
    private static final String TAG_SORT_DATE = "sortDate";
    //tag associated with the FAB menu button that sorts by ratings
    private static final String TAG_SORT_RATINGS = "sortRatings";
    //Run the JobSchedulerService every 2 minutes
    private static final long POLL_FREQUENCY = 28800000;

    private Toolbar mToolbar;
    //a layout grouping the toolbar and the tabs together
    private ViewGroup mContainerToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawer();
//        setupTabs();
        //animate the Toolbar when it comes into the picture
        AnimationUtils.animateToolbarDroppingDown(mContainerToolbar);

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
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }

    public void onDrawerItemClicked(int index) {
        Toast.makeText(this,String.valueOf(index),Toast.LENGTH_SHORT).show();
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
        // Handle action bar item clicks here. The action bar will 
        // automatically handle clicks on the Home/Up button, so long 
        // as you specify a parent activity in AndroidManifest.xml. 
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement 
//        if (id == R.id.action_settings) {
//            L.m("Settings selected");
//            return true;
//        }
//        if (id == R.id.action_touch_intercept_activity) {
////            startActivity(new Intent(this, ActivityTouchEvent.class));
//        }
//
//        if (R.id.action_activity_calling == id) {
////            startActivity(new Intent(this, ActivityA.class));
//        }
//        if (R.id.action_tabs_using_library == id) {
////            startActivity(new Intent(this, ActivitySlidingTabLayout.class));
//        }
//        if (R.id.action_vector_test_activity == id) {
////            startActivity(new Intent(this, ActivityVectorDrawable.class));
//        }
//
//        if (R.id.action_dynamic_tabs_activity == id) {
////            startActivity(new Intent(this, ActivityDynamicTabs.class));
//        }
//        if (R.id.action_recycler_item_animations == id) {
////            startActivity(new Intent(this, ActivityRecylerAnimators.class));
//        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onClick(View v) {
//        //call instantiate item since getItem may return null depending on whether the PagerAdapter is of type FragmentPagerAdapter or FragmentStatePagerAdapter
//        Fragment fragment = (Fragment) mAdapter.instantiateItem(mPager, mPager.getCurrentItem());
//        if (fragment instanceof SortListener) {
//
//            if (v.getTag().equals(TAG_SORT_NAME)) {
//                //call the sort by name method on any Fragment that implements sortlistener
//                ((SortListener) fragment).onSortByName();
//            }
//            if (v.getTag().equals(TAG_SORT_DATE)) {
//                //call the sort by date method on any Fragment that implements sortlistener
//                ((SortListener) fragment).onSortByDate();
//            }
//            if (v.getTag().equals(TAG_SORT_RATINGS)) {
//                //call the sort by ratings method on any Fragment that implements sortlistener
//                ((SortListener) fragment).onSortByRating();
//            }
//        }
//
//    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int icons[] = {R.drawable.ic_action_search_orange,
                R.drawable.ic_action_trending_orange,
                R.drawable.ic_action_upcoming_orange};

        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        public Fragment getItem(int num) {
            Fragment fragment = null;
//            L.m("getItem called for " + num);
            switch (num) {
                case TAB_SEARCH_RESULTS:
//                    fragment = FragmentSearch.newInstance("", "");
                    break;
                case TAB_HITS:
//                    fragment = FragmentBoxOffice.newInstance("", "");
                    break;
                case TAB_UPCOMING:
//                    fragment = FragmentUpcoming.newInstance("", "");
                    break;
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

        private Drawable getIcon(int position) {
            return getResources().getDrawable(icons[position]);
        }
    }
} 