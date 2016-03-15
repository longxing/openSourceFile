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
public class HistoryOrderAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;

    public HistoryOrderAdapter(Context context, List<Map<String, Object>> mData) {
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
            convertView = mInflater.inflate(R.layout.item_history_order, null);
            holder.title = (TextView) convertView.findViewById(R.id.historyOrderItemTitleTextView);
            holder.info = (TextView) convertView.findViewById(R.id.historyOrderItemInfoTextView);
            holder.date = (TextView) convertView.findViewById(R.id.historyOrderItemDateTextView);
            holder.no = (TextView) convertView.findViewById(R.id.historyOrderItemNoTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText((String) mData.get(position).get("title"));
        holder.info.setText((String) mData.get(position).get("info"));
        holder.date.setText((String) mData.get(position).get("date"));
        holder.no.setText((String) mData.get(position).get("no"));

        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
        public TextView info;
        public TextView date;
        public TextView no;
    }
}
