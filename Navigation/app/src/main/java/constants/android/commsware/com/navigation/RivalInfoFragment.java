package constants.android.commsware.com.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
=======
import android.support.v7.widget.Toolbar;
import android.util.Log;
>>>>>>> bd958df7bdf20b20dcba13e57785b9900d9955e8
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RivalInfoFragment extends Fragment {

    String TAG = "RivalInfoFragment : ";

    String name;
    TextView mTVRivalName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

<<<<<<< HEAD
=======
        // Inflate the layout for this fragment
>>>>>>> bd958df7bdf20b20dcba13e57785b9900d9955e8
        View view = inflater.inflate(R.layout.fragment_rival_info, container, false);

        mTVRivalName = (TextView) view.findViewById(R.id.text_detail_name);
        mTVRivalName.setText(getArguments().getString("name"));

        return view;
    }
}
