package com.ailk.openplatform.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.ailk.openplatform.contants.Constants;
import com.ailk.openplatform.entitys.OpenConsumer;
import com.ailk.openplatform.exception.SDKException;
import com.ailk.openplatform.net.AsyncRunner;
import com.ailk.openplatform.net.RequestListener;
import com.ailk.openplatform.utils.LogUtil;
import com.ailk.openplatform.utils.OauthUtil;

public class LoginActivity extends Activity {
	private static final String TAG = "LoginActivity";
	private OpenConsumer mConsumer;
	private Context mContext;
	/** Web 授权接口类，提供登陆等功能 */
	private SDKAuth mSDKAuth;
	/** 获取到的 Code */
	private String mCode;
	/** 获取到的 Token */
	private Oauth2AccessToken mAccessToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = LoginActivity.this;
		Intent intent = getIntent();
		mConsumer = (OpenConsumer) intent
				.getSerializableExtra(OpenConsumer.CONSUMER);

		Constants.AUTHORIZE = intent.getStringExtra("authorize");
		Constants.ACCESS_TOKEN = intent.getStringExtra("access_token"); 
		
		mSDKAuth = new SDKAuth(this, mConsumer.getAppId(),
				mConsumer.getAppSecret(), mConsumer.getCallBackHost(),
				mConsumer.getCallBackScheme());
		mSDKAuth.authorize(new AuthListener(), SDKAuth.OBTAIN_AUTH_CODE);
	}

	/**
	 * 认证授权回调类。
	 */
	class AuthListener implements SDKAuthListener {

		@Override
		public void onComplete(Bundle values) {
			if (null == values) {
				Toast.makeText(LoginActivity.this, "获取code失败！",
						Toast.LENGTH_SHORT).show();
				return;
			}

			String code = values.getString("code");
			if (TextUtils.isEmpty(code)) {
				Toast.makeText(LoginActivity.this, "获取code失败！",
						Toast.LENGTH_SHORT).show();
				return;
			}

			mCode = code;
			fetchTokenAsync(mCode, mConsumer.getAppSecret());
		}

		@Override
		public void onCancel() {
			Toast.makeText(LoginActivity.this, "取消获取code！", Toast.LENGTH_LONG)
					.show();
			finish();
		}

		@Override
		public void onSDKException(SDKException e) {
			finish();
		}
	}

	/**
	 * 异步获取 Token。
	 * 
	 * @param authCode
	 *            授权 Code，该 Code 是一次性的，只能被获取一次 Token
	 * @param appSecret
	 *            应用程序的 APP_SECRET，请务必妥善保管好自己的 APP_SECRET，
	 *            不要直接暴露在程序中，此处仅作为一个DEMO来演示。
	 */
	public void fetchTokenAsync(String authCode, String appSecret) {

		Parameters requestParams = new Parameters();
		requestParams.put(Constants.MapParamName.GRANT_TYPE,
				"authorization_code");
		requestParams.put(Constants.MapParamName.CLIENT_ID,
				mConsumer.getAppId());
		requestParams.put(Constants.MapParamName.CLIENT_SECRET,
				mConsumer.getAppSecret());
		requestParams.put(Constants.MapParamName.CODE, authCode);
		requestParams.put(Constants.MapParamName.REDIRECT_URI,
				mConsumer.getCallBackUrl());

		// 异步请求，获取 Token
		AsyncRunner.requestAsync(Constants.ACCESS_TOKEN, requestParams, "POST",
				new RequestListener() {
					@Override
					public void onComplete(String response) {
						LogUtil.d(TAG, "Response: " + response);

						// 获取 Token 成功
						Oauth2AccessToken token = Oauth2AccessToken
								.parseAccessToken(response);
						if (token != null && token.isSessionValid()) {
							LogUtil.d(TAG, "Success! " + token.toString());

							mAccessToken = token;

							Toast.makeText(LoginActivity.this, "成功获取Token!",
									Toast.LENGTH_SHORT).show();

							SharedPreferences prefs = PreferenceManager
									.getDefaultSharedPreferences(mContext);
							OauthUtil.saveOauth(prefs, mAccessToken);
							finish();
						} else {
							LogUtil.d(TAG, "Failed to receive access token");
						}
					}

					@Override
					public void onSDKException(SDKException e) {
						LogUtil.e(TAG, "onSDKException： " + e.getMessage());
						Toast.makeText(LoginActivity.this, "获取Token失败!",
								Toast.LENGTH_SHORT).show();
					}
				});
	}
}
