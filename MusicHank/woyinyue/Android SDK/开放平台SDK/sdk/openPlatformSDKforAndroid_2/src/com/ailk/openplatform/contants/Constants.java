package com.ailk.openplatform.contants;

/**
 * 常量
 * 
 * @author suntq
 * 
 */
public class Constants {
//	// 临时授权码地址
//	public static String AUTHORIZE_HTTPS = "https://10.1.249.32:8443/OpenOauth2/oauth/authorize";
//
//	// 临时授权码地址
//	public static String AUTHORIZE_HTTP = "http://10.1.249.32:48186/OpenOauth2/oauth/authorize";

	public static String AUTHORIZE = "";
	// 获取授权地址
	public static String ACCESS_TOKEN = "http://10.1.249.32:48186/OpenOauth2/oauth/token";

	public static String GRANT_TYPE_AUTHORIZATION = "authorization_code";
	public static String GRANT_TYPE_TOKEN = "token";

	public static String RESPONSE_KEY = "code";
 
	public static class MapParamName {
		public static String CLIENT_ID = "client_id";
		public static String CLIENT_SECRET = "client_secret";
		public static String RESPONSE_TYPE = "response_type";
		public static String REDIRECT_URI = "redirect_uri";
		public static String GRANT_TYPE = "grant_type";
		public static String CODE = "code";
		public static String DISPLAY = "display";
	}

	public static class RequestParamName {
		public static String ACCESS_TOKEN = "access_token";
		public static String APP_KEY = "appkey";
		public static String TIME_STAMP = "timestamp";
		public static String DIGEST = "digest";
	}

	public static class ResponseParamName {
		public static String ACCESS_TOKEN = "access_token";
		public static String TOKEN_TYPE = "token_type";
		public static String REFRESH_TOKEN = "refresh_token";
		public static String EXPIRES_IN = "expires_in";
	}

}
