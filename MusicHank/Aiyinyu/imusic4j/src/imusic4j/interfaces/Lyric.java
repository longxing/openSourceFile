package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Lyric {
	
	public String getLyricByName(String musicName,String actorName, String type) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("musicName", musicName));
		nvps.add(new BasicNameValuePair("actorName", actorName));
		nvps.add(new BasicNameValuePair("type", type));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.LYRIC_URL, "getlyricbyname", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryLyricByContentID(String contentID,String type) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("contentID", contentID));
		nvps.add(new BasicNameValuePair("type", type));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.LYRIC_URL, "querylyricbycontentid", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryLyricByResourceID(String resourceID,String type) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("resourceID", resourceID));
		nvps.add(new BasicNameValuePair("type", type));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.LYRIC_URL, "querylyricbyresourceid", RestUrlPath.RETURN_TYPE_JSON);
	}
}
