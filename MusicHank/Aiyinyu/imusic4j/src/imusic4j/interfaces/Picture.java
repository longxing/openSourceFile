package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Picture {
	
	public String findBySinger(String actorName) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("actorName", actorName));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PICTURE_URL, "findbysinger", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String findBySingerAndRing(String resourceID, String singer,String song, String type, String reskind, String width,String height) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("resourceID", resourceID));
		nvps.add(new BasicNameValuePair("singer", singer));
		nvps.add(new BasicNameValuePair("song", song));
		nvps.add(new BasicNameValuePair("type", type));
		nvps.add(new BasicNameValuePair("reskind", reskind));
		nvps.add(new BasicNameValuePair("width", width));
		nvps.add(new BasicNameValuePair("height", height));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PICTURE_URL,"findbysingerandring", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String findSingerPics(String id, int idtype,
			String singername, int width, int height, String format,
			int startnum, int maxnum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", id));
		nvps.add(new BasicNameValuePair("idtype", String.valueOf(idtype)));
		nvps.add(new BasicNameValuePair("singername", singername));
		nvps.add(new BasicNameValuePair("width", String.valueOf(width)));
		nvps.add(new BasicNameValuePair("height", String.valueOf(height)));
		nvps.add(new BasicNameValuePair("format", format));
		nvps.add(new BasicNameValuePair("startnum", String.valueOf(startnum)));
		nvps.add(new BasicNameValuePair("maxnum", String.valueOf(maxnum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PICTURE_URL,"findsingerpics", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String findAlbumPics(String id, int idtype, String albumname,
			int width, int height, String format, int startnum, int maxnum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", id));
		nvps.add(new BasicNameValuePair("idtype", String.valueOf(idtype)));
		nvps.add(new BasicNameValuePair("albumname", albumname));
		nvps.add(new BasicNameValuePair("width", String.valueOf(width)));
		nvps.add(new BasicNameValuePair("height", String.valueOf(height)));
		nvps.add(new BasicNameValuePair("format", format));
		nvps.add(new BasicNameValuePair("startnum", String.valueOf(startnum)));
		nvps.add(new BasicNameValuePair("maxnum", String.valueOf(maxnum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.PICTURE_URL,"findalbumpics", RestUrlPath.RETURN_TYPE_JSON);
	}
}
