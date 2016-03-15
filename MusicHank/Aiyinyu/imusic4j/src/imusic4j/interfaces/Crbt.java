package imusic4j.interfaces;

import java.util.ArrayList;
import java.util.List;

import imusic4j.util.HttpUtils;
import imusic4j.RestUrlPath;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Crbt {
	
	public String order(String mdn, String ringID, String type,String randomkey, int setDefaultCrbt) {

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringID", ringID));
		nvps.add(new BasicNameValuePair("type", type));
		nvps.add(new BasicNameValuePair("randomkey", randomkey));
		nvps.add(new BasicNameValuePair("setDefaultCrbt", String.valueOf(setDefaultCrbt)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "order", RestUrlPath.RETURN_TYPE_JSON);

	}
	
	public String orderByImsi(String mdn, String ringID, String type,String imsi, int setDefaultCrbt){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringID", ringID));
		nvps.add(new BasicNameValuePair("type", type));
		nvps.add(new BasicNameValuePair("imsi", imsi));
		nvps.add(new BasicNameValuePair("setDefaultCrbt", String.valueOf(setDefaultCrbt)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "orderbyimsi", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String sendRandomKey(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "sendrandom", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String isCrbtUser(String mdn) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CRBT_URL, "iscrbtuser", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String openCrbt(String mdn, String randomkey){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("randomkey",randomkey));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "open", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String present(String mdn, String tomdn, String randomkey,String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("tomdn", tomdn));
		nvps.add(new BasicNameValuePair("randomkey",randomkey));
		nvps.add(new BasicNameValuePair("ringID", ringID));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "present", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryDafaultRing(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CRBT_URL, "querydefaultring", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String setDefaultRing(String mdn, String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringID", ringID));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "setring", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryRing(String mdn, int startnum, int maxnum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("startnum", String.valueOf(startnum)));
		nvps.add(new BasicNameValuePair("maxnum", String.valueOf(maxnum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CRBT_URL, "queryring", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryPlayMode(String mdn, String mdnType){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("mdnType",mdnType));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CRBT_URL, "queryplaymode", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String setPlayMode(String mdn, int mdnType, int playMode){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("mdnType", String.valueOf(mdnType)));
		nvps.add(new BasicNameValuePair("playMode",String.valueOf(playMode)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "setplaymode", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String wapPush(String mdn, String randomkey, String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("randomkey", String.valueOf(randomkey)));
		nvps.add(new BasicNameValuePair("ringID",String.valueOf(ringID)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "wappush", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String ringtonedownload(String mdn, String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringID",String.valueOf(ringID)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "ringtonedownload", RestUrlPath.RETURN_TYPE_JSON);
	}
	public String ringtonedownloadByImsi(String imsi, String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("imsi", imsi));
		nvps.add(new BasicNameValuePair("ringID",ringID));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.CRBT_URL, "ringtonedownloadByImsi", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String isOrderPackage(String idType, String id,String pid){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("idType", idType));
		nvps.add(new BasicNameValuePair("id",id));
		nvps.add(new BasicNameValuePair("pid",pid));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CRBT_URL, "isOrderPackage", RestUrlPath.RETURN_TYPE_JSON);
		
	}
	
}
