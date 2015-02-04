package constants.android.commsware.com.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import DTO.Rival;

import ListAdapter.RivalAdapter;

public class SelectModeFragment extends Fragment {

    String TAG = "SelectModeFragment : ";

    ArrayList<Rival> mRivals;

    ListView mListSelectMode;
    RivalAdapter mSelectModeAdapter;

    Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_mode, container, false);

        // Set Toolbar icon Visiblity : false
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mToolbar.getMenu().findItem(R.id.action_my_record).setVisible(false);

        // Constructor parameter : String name, int avatar, String mode, String goalTime, String speed
        mRivals = new ArrayList<Rival>();

        mRivals.add(new Rival("Mio", R.drawable.q, getString(R.string.text_basic), "1.00", "2/5"));
        mRivals.add(new Rival("Foxy", R.drawable.q, getString(R.string.text_normal), "1:30", "3/5"));
        mRivals.add(new Rival("Red Eyes", R.drawable.q, getString(R.string.text_hard), "2:00", "4/5"));
        mRivals.add(new Rival("Cheetan", R.drawable.q, getString(R.string.text_event), "4:00", "3/5"));

        // Set ListView & List Adapter
        mListSelectMode = (ListView) view.findViewById(R.id.list_selectMode);
        mSelectModeAdapter = new RivalAdapter(view.getContext(), R.id.list_selectMode, mRivals);
        mListSelectMode.setAdapter(mSelectModeAdapter);

        // Set ListItemClickListener
        mListSelectMode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                // To delivery rival name next Fragment (RivalInfoFragment)
                Bundle arguments = new Bundle();
                TextView name = (TextView) view.findViewById(R.id.text_rival_name);
                arguments.putString("name", name.getText().toString());

                Fragment newFragment = new RivalInfoFragment();
                newFragment.setArguments(arguments);
                ft.replace(R.id.container, newFragment, "View Detail Info");

                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}
