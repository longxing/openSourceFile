package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class MusicSend {
	
	public String commonIvrOperate(String phone, String toPhone,
			int bizType, String cpid, String musicCode, String sendTime,
			String message){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("phone", phone));
		nvps.add(new BasicNameValuePair("toPhone", toPhone));
		nvps.add(new BasicNameValuePair("bizType", String.valueOf(bizType)));
		nvps.add(new BasicNameValuePair("cpid", cpid));
		nvps.add(new BasicNameValuePair("musicCode", musicCode));
		nvps.add(new BasicNameValuePair("sendTime", sendTime));
		nvps.add(new BasicNameValuePair("message", message));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MUSICSEND_URL, "common", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String musicSend(String randomkey, String sendPhone,
			String recPhone, String musicCode, int consumeType, int price,
			String sendTime, String information){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("randomkey", randomkey));
		nvps.add(new BasicNameValuePair("sendPhone", sendPhone));
		nvps.add(new BasicNameValuePair("recPhone", recPhone));
		nvps.add(new BasicNameValuePair("musicCode", musicCode));
		nvps.add(new BasicNameValuePair("consumeType", String.valueOf(consumeType)));
		nvps.add(new BasicNameValuePair("price", String.valueOf(price)));
		nvps.add(new BasicNameValuePair("sendTime", sendTime));
		nvps.add(new BasicNameValuePair("information", information));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MUSICSEND_URL, "send", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String musicSendSms(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MUSICSEND_URL, "sendsms", RestUrlPath.RETURN_TYPE_JSON);
	}
}
