package ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import DTO.Record;
import constants.android.commsware.com.navigation.R;

public class RecordAdapter extends ArrayAdapter<Record> {
    private Context mContext;
    int mlayoutResourceId;
    private ArrayList<Record> mItems;

    public RecordAdapter(Context context, int layoutResourceId, ArrayList<Record> items) {
        super(context, layoutResourceId, items);

        this.mContext = context;
        this.mlayoutResourceId = layoutResourceId;
        this.mItems = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder mHolder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_record_list, null);

            mHolder = new RecordHolder();
            mHolder.pic = (ImageView) row.findViewById(R.id.img_record_pic);
            mHolder.date = (TextView) row.findViewById(R.id.text_record_date);
            mHolder.time = (TextView) row.findViewById(R.id.text_record_time);
            mHolder.isNew = (TextView) row.findViewById(R.id.text_record_new);
            mHolder.result = (TextView) row.findViewById(R.id.text_record_result);

            row.setTag(mHolder);

        } else {
            mHolder = (RecordHolder) row.getTag();
        }

        // need to change by how to set image resource
        mHolder.pic.setImageResource(R.drawable.q);

        mHolder.date.setText(mItems.get(position).getRunningDate());
        mHolder.time.setText(mItems.get(position).getEndTime());

        // need to change by how to know new record
        //if(new condition) {
        //    mHolder.isNew.setVisibility(View.VISIBLE);
        //}

        setResult(mHolder, mItems.get(position));

        return row;
    }

    private void setResult(RecordHolder holder, Record item) {

        if (item.isWin()) {
            holder.result.setText(item.getUserName() + "가 " + item.getRivalName() + mContext.getString(R.string.text_win));
            if (item.getUserName().equals(""))
                holder.result.setText(item.getRivalName() + mContext.getString(R.string.text_win));
        } else
            holder.result.setText(item.getRivalName() + mContext.getString(R.string.text_lose));
    }

    private static class RecordHolder {
        ImageView pic;
        TextView date;
        TextView time;
        TextView isNew;
        TextView result;
    }
}