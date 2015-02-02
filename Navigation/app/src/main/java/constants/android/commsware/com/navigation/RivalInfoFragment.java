package constants.android.commsware.com.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

<<<<<<< HEAD:Navigation/app/src/main/java/constants/android/commsware/com/navigation/SelectRivalFragment.java
public class SelectRivalFragment extends Fragment {
    String tag = "Select";
=======
public class RivalInfoFragment extends Fragment {

    String name = "";
    TextView mTVRivalName;

>>>>>>> 3f817956216ab8842e6ed550fafcc0430a1efe82:Navigation/app/src/main/java/constants/android/commsware/com/navigation/RivalInfoFragment.java
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
<<<<<<< HEAD:Navigation/app/src/main/java/constants/android/commsware/com/navigation/SelectRivalFragment.java
        View view = inflater.inflate(R.layout.fragment_bus, container, false);
        Log.d(tag, "SelectRivalFragment");
=======
        View view = inflater.inflate(R.layout.fragment_rival_info, container, false);

        mTVRivalName = (TextView) view.findViewById(R.id.text_detail_name);
        mTVRivalName.setText(getArguments().getString("name"));
>>>>>>> 3f817956216ab8842e6ed550fafcc0430a1efe82:Navigation/app/src/main/java/constants/android/commsware/com/navigation/RivalInfoFragment.java

        return view;
    }
}
