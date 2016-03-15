package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Cate {
	
	public String queryActorsInfo(String cateID, int startNum,int maxNum, String order) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cateID", cateID));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		nvps.add(new BasicNameValuePair("order", order));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CATE_URL, "queryactorsinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryAlbumsInfo(String cateID, int startNum,int maxNum, String order) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cateID", cateID));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		nvps.add(new BasicNameValuePair("order", order));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CATE_URL, "queryalbumsinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryMusicsInfo(String cateID, int startNum,int maxNum, String order) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cateID", cateID));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		nvps.add(new BasicNameValuePair("order", order));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CATE_URL, "querymusicsinfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryCateList() {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CATE_URL, "querycatelist", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String getMusicTag(String singerName,String musicName) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("singerName", String.valueOf(singerName)));
		nvps.add(new BasicNameValuePair("musicName", String.valueOf(musicName)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CATE_URL, "getmusictag", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String getMusicTagForCms(String mdmcMusicId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdmcMusicId", String.valueOf(mdmcMusicId)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CATE_URL, "getmusictagforcms", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public static void main(String[] args) {
		Cate c = new Cate();
		String musicName ="后会无期";
		String singerName="徐良+汪苏泷";
		System.out.println(":"+c.getMusicTag(singerName, musicName));	
		System.out.println(":"+c.getMusicTagForCms("1031008271"));
	}
}
