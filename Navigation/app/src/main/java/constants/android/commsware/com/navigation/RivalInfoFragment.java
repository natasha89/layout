package constants.android.commsware.com.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RivalInfoFragment extends Fragment {

    String name = "";
    TextView mTVRivalName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rival_info, container, false);

        mTVRivalName = (TextView) view.findViewById(R.id.text_detail_name);
        mTVRivalName.setText(getArguments().getString("name"));

        return view;
    }
}
