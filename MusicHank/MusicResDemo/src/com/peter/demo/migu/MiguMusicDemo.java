package com.peter.demo.migu;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cmsc.cmmusic.common.Logger;
import com.cmsc.cmmusic.common.MusicQueryInterface;
import com.cmsc.cmmusic.common.data.MusicInfo;
import com.cmsc.cmmusic.common.data.MusicListRsp;
import com.peter.demo.R;
import com.peter.demo.media.MediaPlayerManger;

public class MiguMusicDemo extends Activity implements OnItemClickListener {

    private ListView list_music;
    private Button bt_searchButton;
    private EditText et_import_word;
    private ListAdapter mListAdapter;
    private static final int EVENT_UPDATE_UI = 1;
    private List<MusicInfo> infos = new ArrayList<MusicInfo>();
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case EVENT_UPDATE_UI:
                infos = (List<MusicInfo>) msg.obj;
                if (infos != null) {
                    mListAdapter.setList(infos);
                    list_music.setAdapter(mListAdapter);
                } else {
                    Toast.makeText(MiguMusicDemo.this, "没有内容", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_migu_view);
        et_import_word = (EditText) findViewById(R.id.et_search);
        bt_searchButton = (Button) findViewById(R.id.bt_search);
        list_music = (ListView) findViewById(R.id.list_music);
        list_music.setOnItemClickListener(this);
        mListAdapter = new ListAdapter(MiguMusicDemo.this);
        bt_searchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String word = et_import_word.getText().toString();
                        MusicListRsp musicListRsp = null;
                        musicListRsp = MusicQueryInterface.getMusicsByKey(MiguMusicDemo.this, URLEncoder.encode(word),
                                "字符", 1, 5);
                        Message msg = Message.obtain();
                        msg.obj = musicListRsp.getMusics();
                        msg.what = EVENT_UPDATE_UI;
                        mHandler.sendMessage(msg);
                        Log.d("musicInfo", "musicListRsp:" + musicListRsp != null ? musicListRsp + "" : "");
                    }
                }.start();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("MiguMusicDemo","onItemClick postition:"+position);
        if (infos.size() > position) {
            String infoPath = infos.get(position).getSongListenDir();
            Logger.d("MiguMusicDemo", "info path:" + infoPath);
            MediaPlayerManger.getInstance().play(infoPath);
        }
    }
}
