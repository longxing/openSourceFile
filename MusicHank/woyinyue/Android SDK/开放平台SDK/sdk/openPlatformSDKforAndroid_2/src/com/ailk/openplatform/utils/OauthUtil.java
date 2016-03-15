package com.ailk.openplatform.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ailk.openplatform.contants.Constants;
import com.ailk.openplatform.task.Oauth2AccessToken;

/**
 * 
 * @author suntq
 * 
 */
public class OauthUtil {

	public static void saveOauth(SharedPreferences prefs,
			Oauth2AccessToken mAccessToken) {
		if (mAccessToken != null) {
			try {
				Editor editor = prefs.edit();
				editor.putString(Constants.ResponseParamName.ACCESS_TOKEN,
						mAccessToken.getToken());
				editor.putLong(Constants.ResponseParamName.EXPIRES_IN,
						mAccessToken.getExpiresTime());
				editor.putString(Constants.ResponseParamName.REFRESH_TOKEN,
						mAccessToken.getRefreshToken());
				editor.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getAccessToken(SharedPreferences prefs) {
		return prefs.getString(Constants.ResponseParamName.ACCESS_TOKEN, "");
	}

}
