package ListAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import DTO.Rival;
import constants.android.commsware.com.navigation.R;

public class RivalAdapter extends ArrayAdapter<Rival> {
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
            //Log.d(TAG, mHolder.toString());

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

    private static class RivalHolder {
        ImageView pic;
        TextView name;
        TextView mode;
        TextView speed;
        TextView goal;
    }
}

