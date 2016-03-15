package imusic4j.util;

import imusic4j.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

public class HttpUtils {

	private static final Logger logger = Logger.getLogger(HttpUtils.class);

	public static String httpGetTool(List<NameValuePair> nvps, String urlPath,
			String methodName, String reusltType) {
		HttpClient http = new DefaultHttpClient();
		StringBuilder url = new StringBuilder();
		url.append(Configuration.BASEURL + urlPath + "/" + methodName
				+ reusltType);
		if (null != nvps && nvps.size() > 0) {
			url.append("?");
			for (int i = 0; i < nvps.size(); i++) {
				try {
					url.append(nvps.get(i).getName()).append("=").append(
							URLEncoder.encode(nvps.get(i).getValue(),"utf-8")
							).append("&");
				} catch (UnsupportedEncodingException e) {
				
					e.printStackTrace();
				}
			}
			
			if(url.toString().endsWith("&")){
				url.deleteCharAt(url.lastIndexOf("&"));
			}
		}
		HttpGet httpget = new HttpGet(url.toString());
		StringBuffer sb = new StringBuffer();
		try {
			setHeader(httpget, nvps);
			HttpResponse response = http.execute(httpget);
			HttpEntity entity = response.getEntity();
			logger.info("http response code = "
					+ response.getStatusLine().getStatusCode());
			// 显示结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			if (reader != null) {
				reader.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String httpPostTool(List<NameValuePair> nvps, String urlPath,
			String methodName, String reusltType) {
		HttpClient http = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(Configuration.BASEURL + urlPath
				+ methodName + reusltType);
		UrlEncodedFormEntity entityform;
		StringBuffer sb = new StringBuffer();
		try {
			entityform = new UrlEncodedFormEntity(nvps, HTTP.UTF_8);

			setHeader(httpPost, nvps);
			httpPost.setEntity(entityform);
			HttpResponse response = http.execute(httpPost);
			HttpEntity entity = response.getEntity();
			logger.info("http response code = "
					+ response.getStatusLine().getStatusCode());

			// 显示结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			if (reader != null) {
				reader.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

	public static void setHeader(HttpGet httpGet, List<NameValuePair> nvps) {
		httpGet.setHeader("auth-deviceid", Configuration.DEVICEID);
		httpGet.setHeader("auth-channelid", Configuration.CHANNELID);
		java.text.SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhhmmss");
		String timestamp = date.format(new Date());
		httpGet.setHeader("auth-timestamp", timestamp);
		httpGet.setHeader("auth-signature-method",
				Configuration.SIGNATUREMETHOD);

		StringBuffer sb = new StringBuffer();
		sb.append(Configuration.DEVICEID).append("&");
		sb.append(Configuration.CHANNELID).append("&");
		sb.append(timestamp).append("&");
		if (nvps != null && nvps.size() > 0) {
			for (NameValuePair n : nvps) {
				sb.append(n.getValue()).append("&");
			}
		}
		String auth_signature = AuthUtils.generateMacSignature(
				Configuration.DEVICE_SECRET, sb.substring(0, sb.length() - 1));
		httpGet.setHeader("auth-signature", auth_signature);
	}

	private static void setHeader(HttpPost httpPost, List<NameValuePair> nvps) {

		httpPost.setHeader("auth-deviceid", Configuration.DEVICEID);
		httpPost.setHeader("auth-channelid", Configuration.CHANNELID);
		java.text.SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhhmmss");
		String timestamp = date.format(new Date());
		httpPost.setHeader("auth-timestamp", timestamp);
		httpPost.setHeader("auth-signature-method",
				Configuration.SIGNATUREMETHOD);

		StringBuffer sb = new StringBuffer();
		sb.append(Configuration.DEVICEID).append("&");
		sb.append(Configuration.CHANNELID).append("&");
		sb.append(timestamp).append("&");
		if (nvps != null && nvps.size() > 0) {
			for (NameValuePair n : nvps) {
				sb.append(n.getValue()).append("&");
			}
		}

		String auth_signature = AuthUtils.generateMacSignature(
				Configuration.DEVICE_SECRET, sb.substring(0, sb.length() - 1));
		httpPost.setHeader("auth-signature", auth_signature);

	}



}
