package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Product {
	
	public String queryAllProductByMusicInfo(String actorName,String songName, int contentID, String CPID) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("actorName", actorName));
		nvps.add(new BasicNameValuePair("songName", songName));
		nvps.add(new BasicNameValuePair("contentID", String.valueOf(contentID)));
		nvps.add(new BasicNameValuePair("CPID", CPID));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PRODUCT_URL, "queryallproduct", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryCrbtInfo(String mdmcMusicId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PRODUCT_URL, "querycrbtinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryFullTrackInfo(String mdmcMusicId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PRODUCT_URL, "queryfullTrackinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryRingBoxBizInfo(String boxFeeId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("boxFeeId", boxFeeId));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PRODUCT_URL, "queryringboxbizinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryRingBoxInfo(String boxFeeId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("boxFeeId", boxFeeId));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PRODUCT_URL, "queryringboxinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryRingtoneInfo(String mdmcMusicId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PRODUCT_URL, "queryringtoneinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
}
