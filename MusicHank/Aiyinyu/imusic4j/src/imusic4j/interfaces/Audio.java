package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Audio {
	
	public String queryCRBTAudioFile(String mdmcMusicId,String format) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		nvps.add(new BasicNameValuePair("format", format));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.AUDIO_URL, "querycrbt", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryFullTrackAudioFile(String mdmcMusicId,String format) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		nvps.add(new BasicNameValuePair("format", format));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.AUDIO_URL, "queryfulltrack", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryRingtoneAudioFile(String mdmcMusicId,String format) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdmcMusicId", mdmcMusicId));
		nvps.add(new BasicNameValuePair("format", format));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.AUDIO_URL, "queryringtone", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryFullTrackAudioFileById(String id, int idtype,String format) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", id));
		nvps.add(new BasicNameValuePair("idtype", String.valueOf(idtype)));
		nvps.add(new BasicNameValuePair("format", format));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.AUDIO_URL, "queryfulltrackbyid", RestUrlPath.RETURN_TYPE_JSON);
	}
}
