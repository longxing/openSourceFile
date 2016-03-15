package com.ailk.openplatform.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.UUID;

public class Utility {
	private static final String DEFAULT_CHARSET = "UTF-8";

	public static Bundle parseUrl(String url) {
		try {
			URL u = new URL(url);
			Bundle b = decodeUrl(u.getQuery());
			b.putAll(decodeUrl(u.getRef()));
			return b;
		} catch (MalformedURLException e) {
		}
		return new Bundle();
	}

	public static Bundle decodeUrl(String s) {
		Bundle params = new Bundle();
		if (s != null) {
			String[] array = s.split("&");
			for (String parameter : array) {
				String[] v = parameter.split("=");
				try {
					params.putString(URLDecoder.decode(v[0], "UTF-8"),
							URLDecoder.decode(v[1], "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return params;
	}

	public static boolean isChineseLocale(Context context) {
		try {
			Locale locale = context.getResources().getConfiguration().locale;
			if ((Locale.CHINA.equals(locale))
					|| (Locale.CHINESE.equals(locale))
					|| (Locale.SIMPLIFIED_CHINESE.equals(locale))
					|| (Locale.TAIWAN.equals(locale)))
				return true;
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	public static String generateGUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getSign(Context context, String pkgName) {
		PackageInfo packageInfo;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(pkgName,
					64);
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			return null;
		}
		for (int j = 0; j < packageInfo.signatures.length; j++) {
			byte[] str = packageInfo.signatures[j].toByteArray();
			if (str != null) {
				return MD5.hexdigest(str);
			}
		}
		return null;
	}

	public static String safeString(String orignal) {
		return TextUtils.isEmpty(orignal) ? "" : orignal;
	}
}