package com.example.turing.Listener;

import android.content.Context;

import com.turing.androidsdk.asr.VoiceRecognizeListener;

public class MyVoiceRecognizeListener extends MyBasePublicElement implements
		VoiceRecognizeListener {

	public MyVoiceRecognizeListener(Context context) {
		super(context);
	}

	@Override
	public void onRecognizeError(String arg0) {
		mVoiceRecognizeManager.startRecognize();
		mMyHandler.obtainMessage(4).sendToTarget();
	}

	@Override
	public void onRecognizeResult(String result) {
		handleRecognizeResult(result);
	}

	private void handleRecognizeResult(String result) {
		// 识别到话语后，将其发向服务器，进行语义分析，并回答
		mTuringApiManager.requestTuringAPI(result);
		mMyHandler.obtainMessage(3, result).sendToTarget();
	}

	@Override
	public void onRecordEnd() {
	}

	@Override
	public void onRecordStart() {
	}

	@Override
	public void onStartRecognize() {
		// 仅针对百度识别有效
	}

	@Override
	public void onVolumeChange(int arg0) {
		// 仅针对调用讯飞时有效
	}

}
