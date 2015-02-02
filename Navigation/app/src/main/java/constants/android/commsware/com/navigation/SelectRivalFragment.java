package constants.android.commsware.com.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectRivalFragment extends Fragment {
    String tag = "Select";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus, container, false);
        Log.d(tag, "SelectRivalFragment");

        return view;
    }

}
