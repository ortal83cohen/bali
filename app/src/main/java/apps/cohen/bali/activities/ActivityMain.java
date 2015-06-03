package apps.cohen.bali.activities;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import apps.cohen.bali.R;
import apps.cohen.bali.fragments.FragmentDrawer;
import apps.cohen.bali.fragments.FragmentEditList;
import apps.cohen.bali.fragments.FragmentItemLists;
import apps.cohen.bali.fragments.FragmentPersonalInfo;
import apps.cohen.bali.fragments.FragmentPopularItems;
import apps.cohen.bali.login.GooglePlusLogin;
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
    private static final String FRAGMENT_EDIT_LIST = "fragment_edit_list";
    //int corresponding to the id of our JobSchedulerService

    private Toolbar mToolbar;
    private ImageButton mFabButton;
    //a layout grouping the toolbar and the tabs together
    private ViewGroup mContainerToolbar;

    private ViewPager mPager;

    private SlidingTabLayout mTabs;

    private GooglePlusLogin mGooglePlusLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "apps.cohen.bali",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        setupDrawer();
        setupTabs();
        mGooglePlusLogin = new GooglePlusLogin(this, savedInstanceState);
        //animate the Toolbar when it comes into the picture
//        AnimationUtils.animateToolbarDroppingDown(mContainerToolbar);
        Picasso.with(this).setLoggingEnabled(true);

        mFabButton = (ImageButton) findViewById(R.id.fab_button);
        mFabButton.setVisibility(View.GONE);
        mFabButton.setImageDrawable(
                new IconicsDrawable(this, FontAwesome.Icon.faw_plus).color(
                        Color.WHITE).actionBarSize());
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public GooglePlusLogin getGooglePlusLogin() {
        return mGooglePlusLogin;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGooglePlusLogin.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGooglePlusLogin.stop();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mGooglePlusLogin.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

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
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                if (position == 1) {
                    mFabButton.setVisibility(View.VISIBLE);
                } else {
                    mFabButton.setVisibility(View.GONE);
                }

                Log.d(" position ", String.valueOf(position));
                Log.d(" positionOffset ", String.valueOf(positionOffset));
                Log.d(" onPageScrolled ", String.valueOf(positionOffsetPixels));
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("onPageSelected", String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("StateChanged", String.valueOf(state));
            }
        });
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
//        if (id == R.id.action_settings) {
//            return true;
//        }
//

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_EDIT_LIST);
 getSupportFragmentManager()
        .beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter,
                        R.anim.pop_exit)
                .remove( fragment)
                .commit();

        return super.onOptionsItemSelected(item);
    }

    public void openFragmentEditList() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter,
                        R.anim.pop_exit)
                .replace(R.id.container, FragmentEditList.newInstance(),
                        FRAGMENT_EDIT_LIST)
                .addToBackStack(null)
                .commit();

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
                    fragment = FragmentPopularItems.getInstance();
                    break;
                case 1:
                    fragment = FragmentItemLists.newInstance();
                    break;
                case 2:
                    fragment = FragmentPersonalInfo.newInstance();//= FragmentNewItem.newInstance();
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
