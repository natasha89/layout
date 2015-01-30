package constants.android.commsware.com.navigation;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.Record;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    ArrayList<Record> mRecords = new ArrayList<Record>();

    ListView mListTimeLine;
    RecordAdapter mTimelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        //String userName, String rivalName, String runningDate, String endTime, boolean isWin
        mRecords.add(new Record("A", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("", "Mio", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("A", "Red eyes", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), false));
        mRecords.add(new Record("", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), false));

        mListTimeLine = (ListView)findViewById(R.id.list_timeline);
        mTimelineAdapter = new RecordAdapter(this, R.id.list_timeline, mRecords);
        mListTimeLine.setAdapter(mTimelineAdapter);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);



            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
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

        //for customAdapter extends BaseAdapter
        //public int getCount() {
        //    return mItems.size();
        //}

        //public Record getItem(int position) {
        //    return mItems.get(position);
        //}

        //public long getItemId(int position) {
        //    return position;
        //}

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder mHholder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.item_record_list, null);

                mHholder = new ViewHolder();
                mHholder.pic = (ImageView)row.findViewById(R.id.img_record_pic);
                mHholder.date = (TextView)row.findViewById(R.id.text_record_date);
                mHholder.time = (TextView)row.findViewById(R.id.text_record_time);
                mHholder.isNew = (TextView)row.findViewById(R.id.text_record_new);
                mHholder.result = (TextView)row.findViewById(R.id.text_record_result);

                row.setTag(mHholder);
            }
            else { mHholder = (ViewHolder)row.getTag(); }

            //holder.date.setText("");

            return row;
        }

        //picture loaing type?
        //private static class ThumbnailTask extends AsyncTask<Void, Void, Cursor> {
        //    private int mPosition;
        //    private ViewHolder mHolder;

        //    public ThumbnailTask(int position, ViewHolder holder) {
                mPosition = position;
                mHolder = holder;
        //    }

            protected Cursor doInBackground(Void... arg0) {
//                Glide.with(GlideFragment.this)
//                        .load("https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png")
//                        .into(imgGlide);
            }

            protected void onPostExcute(Bitmap bitmap) {
                if (mHolder.position == mPosition)
                    mHolder.pic.setImageBitmap(bitmap);

            }
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
