package com.example.turing.sdkdemo;

import android.content.Context;
import android.os.Handler;

public class MyHandler extends Handler {

	private static MyHandler mMyHandler;
	private MainActivity mMainActivity;

	public static MyHandler getInstance(Context context) {
		if (mMyHandler == null) {
			mMyHandler = new MyHandler(context);
		}
		return mMyHandler;
	}

	public MyHandler(Context context) {
		mMainActivity = (MainActivity) context;
	}

	@Override
	public void handleMessage(android.os.Message msg) {
		switch (msg.what) {
		case 1:
			mMainActivity.ttsManager.startTTS((String) msg.obj);
			mMainActivity.mStatus.setText("��ʼ������" + (String) msg.obj);
			break;

		case 3:
			mMainActivity.mStatus.setText("ʶ������" + msg.obj);
			break;

		case 4:
			mMainActivity.mStatus.setText("��ʼʶ��");
			break;

		default:
			break;
		}
	};
}
