package constants.android.commsware.com.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import DTO.Rival;

public class SelectModeFragment extends Fragment {

    String TAG = "SelectModeFragment : ";

    ArrayList<Rival> mRivals;

    ListView mListSelectMode;
    RivalAdapter mSelectModeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_mode, container, false);

        // Constructor parameter : String name, int avatar, String mode, String goalTime, String speed
        mRivals = new ArrayList<Rival>();

        mRivals.add(new Rival("Mio", R.drawable.q, getString(R.string.text_basic), "1.00", "2/5"));
        mRivals.add(new Rival("Foxy", R.drawable.q, getString(R.string.text_normal), "1:30", "3/5"));
        mRivals.add(new Rival("Red Eyes", R.drawable.q, getString(R.string.text_hard), "2:00", "4/5"));
        mRivals.add(new Rival("Cheetan", R.drawable.q, getString(R.string.text_event), "4:00", "3/5"));

        mListSelectMode = (ListView) view.findViewById(R.id.list_selectMode);
        mSelectModeAdapter = new RivalAdapter(view.getContext(), R.id.list_selectMode, mRivals);
        mListSelectMode.setAdapter(mSelectModeAdapter);

        mListSelectMode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                Bundle arguments = new Bundle();
                TextView name = (TextView)view.findViewById(R.id.text_rival_name);
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

    //for Select Mode CustomListAdapter
    class RivalAdapter extends ArrayAdapter<Rival> {
        private Context mContext;
        int mlayoutResourceId;
        private ArrayList<Rival> mItems;

        public RivalAdapter(Context context, int layoutResourceId, ArrayList<Rival> items) {
            super(context, layoutResourceId, items);

            this.mContext = context;
            this.mlayoutResourceId = layoutResourceId;
            this.mItems = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RivalHolder mHolder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.item_rival_list, null);

                mHolder = new RivalHolder();
                Log.d(TAG, mHolder.toString());

                mHolder.pic = (ImageView) row.findViewById(R.id.img_rival_pic);
                mHolder.name = (TextView) row.findViewById(R.id.text_rival_name);
                mHolder.mode = (TextView) row.findViewById(R.id.text_rival_mode);
                mHolder.speed = (TextView) row.findViewById(R.id.text_rival_speed);
                mHolder.goal = (TextView) row.findViewById(R.id.text_rival_goal);

                row.setTag(mHolder);

            } else {
                mHolder = (RivalHolder) row.getTag();
            }

            // need to change by how to set image resource
            mHolder.pic.setImageResource(mItems.get(position).getPic());

            mHolder.name.setText(mItems.get(position).getName());
            mHolder.mode.setText(mItems.get(position).getMode());
            mHolder.speed.setText(mItems.get(position).getSpeed());
            mHolder.goal.setText(mItems.get(position).getGoalTime());

            return row;
        }
    }

    private static class RivalHolder {
        ImageView pic;
        TextView name;
        TextView mode;
        TextView speed;
        TextView goal;
    }
}
