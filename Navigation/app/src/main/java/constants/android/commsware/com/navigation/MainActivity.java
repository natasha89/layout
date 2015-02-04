package constants.android.commsware.com.navigation;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import DTO.NavDrawerItem;

public class MainActivity extends ActionBarActivity
        implements AdapterView.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mLvDrawerMenu;
    private NavDrawerListAdapter mDrawerMenuAdapter;

    boolean mToggleFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLvDrawerMenu = (ListView) findViewById(R.id.list_slidermenu);

        mToolbar.setLogo(R.drawable.abc_btn_check_material);
        mToolbar.setTitle("Main");
        mToolbar.setSubtitle("Sub title");
        setSupportActionBar(mToolbar); // Show actionbar settings.

        List<NavDrawerItem> menuItems = generateDrawerMenuItems();
        mDrawerMenuAdapter = new NavDrawerListAdapter(getApplicationContext(), menuItems);
        mLvDrawerMenu.setAdapter(mDrawerMenuAdapter);

        mLvDrawerMenu.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                mToolbar.setTitle("Main");
                mToolbar.setSubtitle("Sub title");
            }

            public void onDrawerOpened(View drawerView) {
                mToolbar.setTitle("Open ToolBar");
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            setFragment(0, UserInfoFragment.class);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                setFragment(0, UserInfoFragment.class);
                break;
            case 1:
                setFragment(1, BikeFragment.class);
                break;
            case 2:
                setFragment(2, BusFragment.class);
                break;
            case 3:
                setFragment(3, CarFragment.class);
                break;
            case 4:
                mDrawerLayout.closeDrawer(mLvDrawerMenu);
                mLvDrawerMenu.invalidateViews();
                break;
            case 5:
                mDrawerLayout.closeDrawer(mLvDrawerMenu);
                mLvDrawerMenu.invalidateViews();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mLvDrawerMenu)) {
            mDrawerLayout.closeDrawer(mLvDrawerMenu);
        } else {
            // dialog
            super.onBackPressed();
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void setFragment(int position, Class<? extends Fragment> fragmentClass) {
        try {
            Fragment fragment = fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, fragmentClass.getSimpleName());
            fragmentTransaction.commit();

            mLvDrawerMenu.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(mLvDrawerMenu);
            mLvDrawerMenu.invalidateViews();
        } catch (Exception ex) {
            Log.e("setFragment", ex.getMessage());
        }
    }

    //Drawer Menu Items
    private List<NavDrawerItem> generateDrawerMenuItems() {
        String[] itemsText = getResources().getStringArray(R.array.nav_drawer_items);
        TypedArray itemsIcon = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        List<NavDrawerItem> result = new ArrayList<NavDrawerItem>();
        for (int i = 0; i < itemsText.length; i++) {
            NavDrawerItem item = new NavDrawerItem();
            item.setText(itemsText[i]);
            item.setIcon(itemsIcon.getResourceId(i, -1));
            result.add(item);
        }
        return result;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    // Set ToolBar (instead of Action Bar) icon : using munu item
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager frMgr = getSupportFragmentManager();
        FragmentTransaction fr = frMgr.beginTransaction();
        Fragment newFragment;

        // Set Toggle
        switch (item.getItemId()) {
            case R.id.action_my_record:
                if (!mToggleFlag) {
                    newFragment = new MyRecordFragment();
                    fr.replace(R.id.container, newFragment, "My Record");

                    mToggleFlag = true;
                }
                else {
                    newFragment = new UserInfoFragment();
                    fr.replace(R.id.container, newFragment, "My Timeline");

                    mToggleFlag = false;
                }

                fr.commit();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
