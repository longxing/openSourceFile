package com.example.turing.Listener;

import org.json.JSONException;
import org.json.JSONObject;

import com.turing.androidsdk.HttpRequestWatcher;

import android.content.Context;
import android.widget.Toast;

public class MyHttpRequestWatcher extends MyBasePublicElement implements HttpRequestWatcher {

	public MyHttpRequestWatcher(Context context) {
		super(context);
	}

	@Override
	public void onSuceess(String arg0) {

		// api请求内容后，服务器返回数据获取位置
		try {
			JSONObject jsonObject = new JSONObject(arg0);
			if (jsonObject.has("text")) {
				mMyHandler.obtainMessage(1, jsonObject.get("text")).sendToTarget();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onError(String arg0) {
		Toast.makeText(mMainActivity, arg0, Toast.LENGTH_SHORT).show();
	}

}
