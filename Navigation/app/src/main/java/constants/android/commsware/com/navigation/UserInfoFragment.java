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
import java.util.Calendar;
import java.util.Date;

import DTO.Record;

public class UserInfoFragment extends Fragment {

    String tag = "UserInfoFragment";

    ArrayList<Record> mRecords;

    ListView mListTimeLine;
    RecordAdapter mTimelineAdapter;

    Button mBtnStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        // Constructor parameter : String userName, String rivalName, String runningDate, String endTime, boolean isWin
        mRecords = new ArrayList<Record>();

        // set test data
        mRecords.add(new Record("A", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("", "Mio", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        mRecords.add(new Record("A", "Red eyes", new SimpleDateFormat("yyyy MM dd").format(cal.getTime()), new SimpleDateFormat("HH mm").format(new Date()), false));

        cal.add(Calendar.DATE, -5);
        mRecords.add(new Record("", "Foxy", new SimpleDateFormat("yyyy MM dd").format(cal.getTime()), new SimpleDateFormat("HH mm").format(new Date()), false));

        mListTimeLine = (ListView) view.findViewById(R.id.list_timeline);
        mTimelineAdapter = new RecordAdapter(view.getContext(), R.id.list_timeline, mRecords);
        mListTimeLine.setAdapter(mTimelineAdapter);

        mBtnStart = (Button) view.findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();


                // hide x
                Fragment newFragment = new SelectModeFragment();
                ft.replace(R.id.container, newFragment, "select Rival");


                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Log.d(tag, "UserInfoFragment");
        return view;
    }

    //for User Timeline CustomListAdapter
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
            RecordHolder mHolder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.item_record_list, null);

                mHolder = new RecordHolder();
                mHolder.pic = (ImageView) row.findViewById(R.id.img_record_pic);
                mHolder.date = (TextView) row.findViewById(R.id.text_record_date);
                mHolder.time = (TextView) row.findViewById(R.id.text_record_time);
                mHolder.isNew = (TextView) row.findViewById(R.id.text_record_new);
                mHolder.result = (TextView) row.findViewById(R.id.text_record_result);

                row.setTag(mHolder);

            } else {
                mHolder = (RecordHolder) row.getTag();
            }

            // need to change by how to set image resource
            mHolder.pic.setImageResource(R.drawable.q);

            mHolder.date.setText(mItems.get(position).getRunningDate());
            mHolder.time.setText(mItems.get(position).getEndTime());

            // need to change by how to know new record
            //if(new condition) {
            //    mHolder.isNew.setVisibility(View.VISIBLE);
            //}

            setResult(mHolder, mItems.get(position));

            return row;
        }

        private void setResult(RecordHolder holder, Record item) {

            if (item.isWin()) {
                holder.result.setText(item.getUserName() + "가 " + item.getRivalName() + getString(R.string.text_win));
                if (item.getUserName().equals(""))
                    holder.result.setText(item.getRivalName() + getString(R.string.text_win));
            } else
                holder.result.setText(item.getRivalName() + getString(R.string.text_lose));
        }
    }

    private static class RecordHolder {
        ImageView pic;
        TextView date;
        TextView time;
        TextView isNew;
        TextView result;
    }
}
