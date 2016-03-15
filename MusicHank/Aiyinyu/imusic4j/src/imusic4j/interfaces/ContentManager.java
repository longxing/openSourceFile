package imusic4j.interfaces;

import java.util.ArrayList;
import java.util.List;

import imusic4j.util.HttpUtils;
import imusic4j.RestUrlPath;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ContentManager {

	public String queryContentInfo(int contentID) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("contentID",String.valueOf(contentID)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENTMANAGER_URL, "querycontentinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
}
