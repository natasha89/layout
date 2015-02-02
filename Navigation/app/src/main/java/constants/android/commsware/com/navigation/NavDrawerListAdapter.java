package constants.android.commsware.com.navigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import DTO.NavDrawerItem;

/**
 * Created by yong on 15. 2. 2.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    List<NavDrawerItem> mItems;
    Context mContext;

    public NavDrawerListAdapter(Context context, List<NavDrawerItem> mItems) {
        this.mItems = mItems;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.title);

        NavDrawerItem item = mItems.get(position);

        imgIcon.setImageResource(item.getIcon());
        tvTitle.setText(item.getText());

        return convertView;
    }
}
