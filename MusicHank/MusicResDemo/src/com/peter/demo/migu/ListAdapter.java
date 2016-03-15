/**
 * 
 */
package com.peter.demo.migu;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cmsc.cmmusic.common.data.MusicInfo;
import com.peter.demo.R;

/**
 * @author Andy
 * 
 */
public class ListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<MusicInfo> infoList = new ArrayList<MusicInfo>();
 
    public ListAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }
    
    public void  setList(List<MusicInfo> infos) {
        infoList = infos;
    }

    @Override
    public int getCount() {
        // TODO 自动生成的方法存根
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO 自动生成的方法存根
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO 自动生成的方法存根
        return position;
    }

    @SuppressWarnings("unused")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (mViewHolder == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.migu_music_item, null);
            mViewHolder.songName = (TextView) convertView.findViewById(R.id.songName);
            mViewHolder.singerName = (TextView) convertView.findViewById(R.id.artist);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.songName.setText(infoList.get(position).getSongName());
        mViewHolder.singerName.setText(infoList.get(position).getSingerName());
        return convertView;
    }

    public final class ViewHolder {
        public TextView songName;
        public TextView singerName;
    }

}
