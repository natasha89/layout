package constants.android.commsware.com.navigation;


import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.NavDrawerItem;
import DTO.Record;


public class MainActivity extends ActionBarActivity
        implements AdapterView.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mLvDrawerMenu;
    private NavDrawerListAdapter mDrawerMenuAdapter;

    ArrayList<Record> mRecords = new ArrayList<Record>();

    ListView mListTimeLine;
    RecordAdapter mTimelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLvDrawerMenu = (ListView) findViewById(R.id.list_slidermenu);

        //String userName, String rivalName, String runningDate, String endTime, boolean isWin
        mRecords.add(new Record("A", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("", "Mio", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("A", "Red eyes", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), false));
        mRecords.add(new Record("", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), false));

        mListTimeLine = (ListView) findViewById(R.id.list_timeline);
        mTimelineAdapter = new RecordAdapter(this, R.id.list_timeline, mRecords);
        mListTimeLine.setAdapter(mTimelineAdapter);

        mToolbar.setLogo(R.drawable.abc_btn_check_material);
        mToolbar.setTitle("Main");
        mToolbar.setSubtitle("Sub title");
        //setSupportActionBar(mToolbar); // Show settings.

        List<NavDrawerItem> menuItems = generateDrawerMenuItems();
        mDrawerMenuAdapter = new NavDrawerListAdapter(getApplicationContext(), menuItems);
        mLvDrawerMenu.setAdapter(mDrawerMenuAdapter);

        mLvDrawerMenu.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                mToolbar.setTitle("Main");
                mToolbar.setSubtitle("Sub title");
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                mToolbar.setTitle("Open ToolBar");
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            setFragment(0, BikeFragment.class);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                setFragment(0, BikeFragment.class);
                break;
            case 1:
                setFragment(1, BusFragment.class);
                break;
            case 2:
                setFragment(2, CarFragment.class);
                break;
            case 3:
                mDrawerLayout.closeDrawer(mLvDrawerMenu);
                mLvDrawerMenu.invalidateViews();
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

    //for Timeline CustomListAdapter
    class RecordAdapter extends ArrayAdapter<Record> {
        private Context mContext;
        int mlayoutResourceId;
        private ArrayList<Record> mItems;

        public RecordAdapter(Context context, int layoutResourceId, ArrayList<Record> items) {
            super(context, layoutResourceId, items);

            this.mContext = context;
            this.mlayoutResourceId = layoutResourceId;
            this.mItems = items;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder mHholder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.item_record_list, null);

                mHholder = new ViewHolder();
                mHholder.pic = (ImageView) row.findViewById(R.id.img_record_pic);
                mHholder.date = (TextView) row.findViewById(R.id.text_record_date);
                mHholder.time = (TextView) row.findViewById(R.id.text_record_time);
                mHholder.isNew = (TextView) row.findViewById(R.id.text_record_new);
                mHholder.result = (TextView) row.findViewById(R.id.text_record_result);

                row.setTag(mHholder);
            } else {
                mHholder = (ViewHolder) row.getTag();
            }

            //holder.date.setText("");

            return row;
        }

    }

    private static class ViewHolder {
        ImageView pic;
        TextView date;
        TextView time;
        TextView isNew;
        TextView result;
        int position;
    }
}
