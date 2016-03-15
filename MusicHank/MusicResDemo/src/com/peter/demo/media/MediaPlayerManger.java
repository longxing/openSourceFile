/**
 * 
 */
package com.peter.demo.media;

import java.io.IOException;

import android.media.MediaPlayer;

/**
 * @author Andy
 * 
 */
public class MediaPlayerManger {

    private MediaPlayer mMediaPlayer;

    private MediaPlayerManger() {
        mMediaPlayer = new MediaPlayer();
    }

    private static class MediaManagerInstone {
        public static MediaPlayerManger instance = new MediaPlayerManger();
    }

    public static MediaPlayerManger getInstance() {
        return MediaManagerInstone.instance;
    }

    public synchronized void play(String path) {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        } else {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
