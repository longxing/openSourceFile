package com.example.androidappexample;

import java.util.HashMap;
import java.util.Map;

import android.os.AsyncTask;
import android.widget.TextView;

import com.ailk.openplatform.entitys.OpenPlatformProvider;

public class HttpRequestTask extends AsyncTask<Boolean, Void, String> {
	private OpenPlatformProvider provider;
	private Map<String,String> params;
	private TextView tv;

	public HttpRequestTask(Map<String,String> params, OpenPlatformProvider provider,TextView tv) {
		super();
		this.params = params;
		this.provider = provider;
		this.tv=tv;
	}

	@Override
	protected String doInBackground(Boolean... arg0) {
		String result="";
		try {
			//String result=provider.doPost(params);
			Map<String,String> headers=new HashMap<String, String>();
			headers.put("head1", "suntq");
			headers.put("head3", "suntq3");
			//String result2=provider.doPost(headers,"日照香炉生紫烟","utf-8",true);
			String result2=provider.doPost(headers,this.params,arg0[0]);
			if(result2!=null){
				System.out.println(result2);
			}
			result=result2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		tv.setText(result);
	}

}
