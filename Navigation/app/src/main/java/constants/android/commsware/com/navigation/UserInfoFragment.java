package constants.android.commsware.com.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
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
<<<<<<< HEAD

    String tag = "UserInfoFragment";
=======
>>>>>>> bd958df7bdf20b20dcba13e57785b9900d9955e8

    ArrayList<Record> mRecords;

    ListView mListTimeLine;
    RecordAdapter mTimelineAdapter;

    Button mBtnStart;

    Toolbar mToolbar;

    // To confirm first fragment Create
    boolean mIsFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        // Constructor parameter : String userName, String rivalName, String runningDate, String endTime, boolean isWin
        mRecords = new ArrayList<Record>();

        mRecords.add(new Record("A", "Foxy", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));
        mRecords.add(new Record("", "Mio", new SimpleDateFormat("yyyy MM dd").format(new Date()), new SimpleDateFormat("HH mm").format(new Date()), true));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        mRecords.add(new Record("A", "Red eyes", new SimpleDateFormat("yyyy MM dd").format(cal.getTime()), new SimpleDateFormat("HH mm").format(new Date()), false));

        cal.add(Calendar.DATE, -5);
        mRecords.add(new Record("", "Foxy", new SimpleDateFormat("yyyy MM dd").format(cal.getTime()), new SimpleDateFormat("HH mm").format(new Date()), false));

        // Set ListView & List Adapter
        mListTimeLine = (ListView) view.findViewById(R.id.list_timeline);
        mTimelineAdapter = new RecordAdapter(view.getContext(), R.id.list_timeline, mRecords);
        mListTimeLine.setAdapter(mTimelineAdapter);

        // Set Game Start Button & listener
        mBtnStart = (Button) view.findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

<<<<<<< HEAD

                // hide x
                Fragment newFragment = new SelectModeFragment();
                ft.replace(R.id.container, newFragment, "select Rival");


=======
                Fragment newFragment = new SelectModeFragment();
                ft.replace(R.id.container, newFragment, "select Rival");

>>>>>>> bd958df7bdf20b20dcba13e57785b9900d9955e8
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!mIsFirst) {
            mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            mToolbar.getMenu().findItem(R.id.action_my_record).setVisible(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mIsFirst) {
            mIsFirst = false;
        }
    }
}
