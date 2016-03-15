package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ActorAlbumInfo {
	
	public String  getActorInfo(String id,int idtype,String actorname) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", id));
		nvps.add(new BasicNameValuePair("idtype", String.valueOf(idtype)));
		nvps.add(new BasicNameValuePair("actorname", actorname));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.ACTORALBUM_URL, "getactorinfo", RestUrlPath.RETURN_TYPE_XML);
	}
	
	public String  findMusicOfActor(String actorID,String startNum, String maxNum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("actorID", actorID));
		nvps.add(new BasicNameValuePair("startNum", startNum));
		nvps.add(new BasicNameValuePair("maxNum", maxNum));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.ACTORALBUM_URL, "findmusicofactor", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String  findMusicsByAlbumId(String albumId,String startNum, String maxNum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("albumId", albumId));
		nvps.add(new BasicNameValuePair("startNum", startNum));
		nvps.add(new BasicNameValuePair("maxNum", maxNum));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.ACTORALBUM_URL, "findmusicsbyalbumid", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String findAlbumsByActor(String actorId,String startNum, String maxNum, String order) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("actorId", actorId));
		nvps.add(new BasicNameValuePair("startNum", startNum));
		nvps.add(new BasicNameValuePair("maxNum", maxNum));
		nvps.add(new BasicNameValuePair("order", order));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.ACTORALBUM_URL, "findalbumsbyactor", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String  findAlbumBySong(String id,int idtype,String songname) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", id));
		nvps.add(new BasicNameValuePair("idtype", String.valueOf(idtype)));
		nvps.add(new BasicNameValuePair("songname", songname));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.ACTORALBUM_URL, "findalbumbysong", RestUrlPath.RETURN_TYPE_JSON);
	}
}
