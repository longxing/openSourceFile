package com.ailk.openplatform.task;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;

import com.ailk.openplatform.contants.Constants;
import com.ailk.openplatform.entitys.OpenConsumer;
import com.ailk.openplatform.utils.NetworkHelper;
import com.ailk.openplatform.utils.URLUtil;

public class SDKAuth {
	public static final String TAG = "web_login";

	protected final static String MOBILE = "mobile";
	public static final int OBTAIN_AUTH_CODE = 0;
	public static final int OBTAIN_AUTH_TOKEN = 1;
	private Context mContext;
	private OpenConsumer mConsumer;

	public SDKAuth(Context context, String appId, String appSecret,
			String callBackHost, String callBackScheme) {
		this.mContext = context;
		this.mConsumer = new OpenConsumer(appId, appSecret, callBackHost,
				callBackScheme);
	}

	public SDKAuth(Context context, OpenConsumer consumer) {
		this.mContext = context;
		this.mConsumer = consumer;
	}

	public SDKAuth(Context context) {
		this.mContext = context;
	}

	public OpenConsumer getConsumer() {
		return this.mConsumer;
	}

	public void setConsumer(OpenConsumer consumer) {
		this.mConsumer = consumer;
	}

	public void anthorize(SDKAuthListener listener) {
		authorize(listener, 1);
	}

	public void authorize(SDKAuthListener listener, int type) {
		startDialog(listener, type);
	}

	private void startDialog(SDKAuthListener listener, int type) {
		if (listener == null) {
			return;
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.MapParamName.CLIENT_ID, mConsumer.getAppId());
		params.put(Constants.MapParamName.RESPONSE_TYPE, Constants.RESPONSE_KEY);
		params.put(Constants.MapParamName.REDIRECT_URI,
				mConsumer.getCallBackUrl());
		params.put(Constants.MapParamName.DISPLAY, MOBILE);
		String requestUrl = null;
		try {
			requestUrl = URLUtil.createUrl(Constants.AUTHORIZE, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!NetworkHelper.hasInternetPermission(this.mContext)) {
			new AlertDialog.Builder(this.mContext)
					.setTitle("Error")
					.setMessage(
							"Application requires permission to access the Internet")
					.create().show();
		} else {
			new SDKDialog(this.mContext, requestUrl, listener, this).show();
		}
	}

}