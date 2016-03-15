package com.asiainfo.android.wmm.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.asiainfo.android.wmm.demo.R;


import java.util.List;
import java.util.Map;

/**
 *
 */
public class ShowRightAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;

    public ShowRightAdapter(Context context, List<Map<String, Object>> mData) {
        Context context1 = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_show_right, null);
            holder.title = (TextView) convertView.findViewById(R.id.showRightItemTitleTextView);
            holder.info = (TextView) convertView.findViewById(R.id.showRightItemInfoTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText((String) mData.get(position).get("title"));
        holder.info.setText((String) mData.get(position).get("info"));

        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
        public TextView info;
    }
}
