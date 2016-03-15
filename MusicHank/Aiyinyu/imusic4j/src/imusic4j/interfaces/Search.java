package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Search {
	
	public String searchMusic(String keyWord, String type,int startNum, int maxNum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("keyWord", keyWord));
		nvps.add(new BasicNameValuePair("type", type));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.SEARCH_URL, "searchmusic", RestUrlPath.RETURN_TYPE_JSON);
	}
}
