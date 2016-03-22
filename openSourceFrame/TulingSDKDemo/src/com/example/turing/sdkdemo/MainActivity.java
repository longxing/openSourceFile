package com.example.turing.sdkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.turing.Listener.MyHttpRequestWatcher;
import com.example.turing.Listener.MyInitListener;
import com.example.turing.Listener.MyTTSListener;
import com.example.turing.Listener.MyVoiceRecognizeListener;
import com.tuling.sdkdemo.R;
import com.turing.androidsdk.TuringApiConfig;
import com.turing.androidsdk.TuringApiManager;
import com.turing.androidsdk.asr.VoiceRecognizeListener;
import com.turing.androidsdk.asr.VoiceRecognizeManager;
import com.turing.androidsdk.tts.TTSManager;

public class MainActivity extends Activity {

	public VoiceRecognizeManager mVoiceRecognizeManager;
	public TuringApiManager mTuringApiManager;
	public TTSManager ttsManager;
	public String myWakeUpWords;
	public TextView mStatus;
	public TuringApiConfig turingApiConfig;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initApplication();

		addListener();

		ttsManager.startTTS("��ð�");
	}

	/**
	 * ��ʼ������ӿڹ�����
	 * 
	 * @author changjingpei
	 * @date 2015��11��13�� ����4:19:30
	 */
	private void initTulingApiManager() {

		turingApiConfig = new TuringApiConfig(this, "fe9b2a4dc69ec54f0169e89711138cab");//SDK������ͯ��ߵ��˺�

		turingApiConfig.setInitListener(new MyInitListener(){

			@Override
			public void onFail() {
				super.onFail();
			}

			@Override
			public void onComplete() {
				super.onComplete();
				mTuringApiManager = new TuringApiManager(turingApiConfig, MainActivity.this);
			}
			
		});


		turingApiConfig.init(this);

	}

	/**
	 * ��ʼ��ʶ���tts
	 * 
	 * @author changjingpei
	 * @date 2015��11��13�� ����4:18:46
	 */
	private void initApplication() {
		// ʶ�������
		mVoiceRecognizeManager = new VoiceRecognizeManager(this, "InhiNPRGdtkqtuQT6Ep0Xboc", "L4n25lKxqbGxGbKN4bzBseRnzuEy8M6E");
		// tts������
		ttsManager = new TTSManager(this, "InhiNPRGdtkqtuQT6Ep0Xboc",
				"L4n25lKxqbGxGbKN4bzBseRnzuEy8M6E");

		mStatus = (TextView) findViewById(R.id.tv_status);

		initTulingApiManager();
	}

	private void addListener() {
		mTuringApiManager.setRequestWatcher(new MyHttpRequestWatcher(this));
		VoiceRecognizeListener listener = new MyVoiceRecognizeListener(this);
		mVoiceRecognizeManager.setmRecognizeListener(listener);
		ttsManager.setmTTSListener(new MyTTSListener(this));
	}
}
