package constants.android.commsware.com.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DTO.Record;

public class UserInfoFragment extends Fragment {
    String tag = "UserInfoFragment";
    public UserInfoFragment() {
        // Required empty public constructor
    }

    ArrayList<Record> mRecords = new ArrayList<Record>();

    ListView mListTimeLine;
    RecordAdapter mTimelineAdapter;

    Button mBtnStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_userinfo, container, false);

        // Constructor parameter : String userName, String rivalName, String runningDate, String endTime, boolean isWin
        mRecords.add(new Record("A", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("", "Mio", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("A", "Red eyes", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), false));
        mRecords.add(new Record("", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), false));

        mListTimeLine = (ListView) view.findViewById(R.id.list_timeline);
        mTimelineAdapter = new RecordAdapter(view.getContext(), R.id.list_timeline, mRecords);
        mListTimeLine.setAdapter(mTimelineAdapter);

        mBtnStart = (Button) view.findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment fragment = new SelectRivalFragment();
                Fragment f = new UserInfoFragment();
                ft.hide(f);
                ft.replace(R.id.container, fragment, "select Rival");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Log.d(tag, "UserInfoFragment");
        return view;
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
            ViewHolder mHolder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.item_record_list, null);

                mHolder = new ViewHolder();
                mHolder.pic = (ImageView) row.findViewById(R.id.img_record_pic);
                mHolder.date = (TextView) row.findViewById(R.id.text_record_date);
                mHolder.time = (TextView) row.findViewById(R.id.text_record_time);
                mHolder.isNew = (TextView) row.findViewById(R.id.text_record_new);
                mHolder.result = (TextView) row.findViewById(R.id.text_record_result);

                setResult(mHolder, mItems.get(position));

                row.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) row.getTag();
            }

            return row;
        }

        private void setResult(ViewHolder holder, Record item) {

            if (item.isWin()) {
                holder.result.setText(item.getUserName() + "가 " + item.getRivalName() + getString(R.string.text_win));
                if(item.getUserName().equals(""))
                    holder.result.setText(item.getRivalName() + getString(R.string.text_win));
            }
            else
                holder.result.setText(item.getRivalName() + getString(R.string.text_lose));
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
