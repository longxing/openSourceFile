package com.example.androidappexample;

import java.util.Map;

import com.ailk.openplatform.entitys.OpenPlatformProvider;

import android.os.AsyncTask;
import android.widget.TextView;

public class HttpRequestGetTask extends AsyncTask<Boolean, Void, String> {
	private OpenPlatformProvider provider;
	private Map<String,String> params;
	private TextView tv;
	public HttpRequestGetTask(OpenPlatformProvider provider,
			Map<String, String> params,TextView tv) {
		super();
		this.provider = provider;
		this.params = params;
		this.tv=tv;
	}

	@Override
	protected String doInBackground(Boolean... arg0) {
		String result2="";
		try {
			result2 = provider.doGet(params, arg0[0]);
			if(result2!=null){
				System.out.println(result2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result2;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		tv.setText(result);
	}
}
