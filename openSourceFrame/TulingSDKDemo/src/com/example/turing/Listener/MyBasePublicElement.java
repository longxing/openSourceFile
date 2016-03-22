package com.example.turing.Listener;

import android.content.Context;
import com.example.turing.sdkdemo.MainActivity;
import com.example.turing.sdkdemo.MyHandler;
import com.turing.androidsdk.TuringApiManager;
import com.turing.androidsdk.asr.VoiceRecognizeManager;

public class MyBasePublicElement {

	public MyHandler mMyHandler;
	public String myWakeUpWords;
	public TuringApiManager mTuringApiManager;
	public VoiceRecognizeManager mVoiceRecognizeManager;
	public MainActivity mMainActivity;
	
	public MyBasePublicElement(Context context) {
		mMyHandler = MyHandler.getInstance(context);
		mMainActivity = (MainActivity) context;  
		// 获取自定义唤醒词
		myWakeUpWords = mMainActivity.myWakeUpWords;
		mTuringApiManager = mMainActivity.mTuringApiManager;
		mVoiceRecognizeManager = mMainActivity.mVoiceRecognizeManager;
	}
}
