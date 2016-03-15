package imusic4j.interfaces;

import java.util.ArrayList;
import java.util.List;

import imusic4j.util.HttpUtils;
import imusic4j.RestUrlPath;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Ivr {
	
	public String order(String mdn, String ringID, String type,int setDefaultCrbt) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringID", ringID));
		nvps.add(new BasicNameValuePair("type", type));
		nvps.add(new BasicNameValuePair("setDefaultCrbt", String.valueOf(setDefaultCrbt)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.IVR_URL, "order", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String isCrbtUser(String mdn) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.IVR_URL, "iscrbtuser", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String openCrbt(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.IVR_URL, "open", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String present(String mdn, String tomdn,String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("tomdn", tomdn));
		nvps.add(new BasicNameValuePair("ringID", ringID));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.IVR_URL, "present", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryDafaultRing(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.IVR_URL, "querydefaultring", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String setDefaultRing(String mdn, String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringID", ringID));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.IVR_URL, "setring", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryRing(String mdn, int startnum, int maxnum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("startnum", String.valueOf(startnum)));
		nvps.add(new BasicNameValuePair("maxnum", String.valueOf(maxnum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.IVR_URL, "queryring", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String wapPush(String mdn,String ringID){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringID",String.valueOf(ringID)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.IVR_URL, "wappush", RestUrlPath.RETURN_TYPE_JSON);
	}
}
