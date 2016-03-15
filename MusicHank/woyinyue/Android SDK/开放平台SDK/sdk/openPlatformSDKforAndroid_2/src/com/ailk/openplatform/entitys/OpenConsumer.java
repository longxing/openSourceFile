package com.ailk.openplatform.entitys;

import java.io.Serializable;
  
/**
 * 
 * @author suntq
 * 
 */
public class OpenConsumer implements Serializable {

	private static final long serialVersionUID = -32130307612913810L;
	public final static String CONSUMER = "consumer";
	public final static String BACK_ACTIVITY = "back_actity";
	private String appId;
	private String appSecret;
	private String callBackHost;
	private String callBackScheme;

	public OpenConsumer(String appId, String appSecret, String callBackHost,
			String callBackScheme) {
		this.appId = appId;
		this.appSecret = appSecret;
		this.callBackHost = callBackHost;
		this.callBackScheme = callBackScheme;
	}

	public String getCallBackUrl() { 
		return this.callBackScheme;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getCallBackHost() {
		return callBackHost;
	}

	public void setCallBackHost(String callBackHost) {
		this.callBackHost = callBackHost;
	}

	public String getCallBackScheme() {
		return callBackScheme;
	}

	public void setCallBackScheme(String callBackScheme) {
		this.callBackScheme = callBackScheme;
	}
}
