package constants.android.commsware.com.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DTO.Record;
import ListAdapter.RecordAdapter;

public class UserInfoFragment extends Fragment {

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
        return view;
    }
}
