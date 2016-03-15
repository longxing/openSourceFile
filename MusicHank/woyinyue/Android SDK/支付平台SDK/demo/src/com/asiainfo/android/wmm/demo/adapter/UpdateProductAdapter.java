package com.asiainfo.android.wmm.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.asiainfo.android.wmm.demo.R;


import java.util.List;
import java.util.Map;

public class UpdateProductAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;

    public UpdateProductAdapter(Context context, List<Map<String, Object>> mData) {
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
            convertView = mInflater.inflate(R.layout.item_product_list, null);
            holder.img = (ImageView) convertView.findViewById(R.id.productListItemImageView);
            holder.title = (TextView) convertView.findViewById(R.id.productListItemTitleTextView);
            holder.info = (TextView) convertView.findViewById(R.id.productListItemSubTextView);
            holder.amount = (TextView) convertView.findViewById(R.id.productListItemAmountTextView);
            holder.button = (Button) convertView.findViewById(R.id.productListItemButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
        holder.title.setText((String) mData.get(position).get("title"));
        holder.info.setText((String) mData.get(position).get("info"));
        holder.amount.setText((String) mData.get(position).get("amount"));
        holder.button.setText((String) mData.get(position).get("btnName"));

        holder.button.setOnClickListener((View.OnClickListener) mData.get(position).get("btnMethod"));
        return convertView;
    }

    public final class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
        public TextView amount;
        public Button button;
    }
}
