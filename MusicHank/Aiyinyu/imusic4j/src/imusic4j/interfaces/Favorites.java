package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Favorites {
	
	public String addCollectSong(String mdn, String mdmcMusicId,
			String singer, String song) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		nvps.add(new BasicNameValuePair("singer", singer));
		nvps.add(new BasicNameValuePair("song", song));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.FAVORITES_URL, "addCollectSong", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String delCollectSong(String mdn, String mdmcMusicId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.FAVORITES_URL, "delCollectSong", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String getCollectSongList(String mdn, int startNum,int maxNum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.FAVORITES_URL, "getCollectSongList", RestUrlPath.RETURN_TYPE_JSON);
	}
}
