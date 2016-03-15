package imusic4j.interfaces;

import java.util.ArrayList;
import java.util.List;

import imusic4j.util.HttpUtils;
import imusic4j.RestUrlPath;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Content {

	public String queryContentBillboardInfo(String billboardType,int startNum,int maxNum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("billboardType", billboardType));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryContentBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryAblumListInfo(String billboardType,int startNum,int maxNum) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("billboardType", billboardType));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryAblumListInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryBillboardListInfo(){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryBillboardListInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryInfomationListInfo(int parentId){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("parentId", String.valueOf(parentId)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryInfomationListInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryWeekBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryWeekBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryNewSongBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryNewSongBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryClassicBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryClassicBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryCampusBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryCampusBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}

	public String queryFunnyBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryFunnyBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryPopularBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryPopularBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryOuMeiBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryOuMeiBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}	
	
	public String queryRiHanBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryRiHanBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}	
	
	public String queryMemberBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryMemberBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}		
	
	public String queryFreeBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryFreeBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}		

	public String queryHotBillboardInfo(int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryHotBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}		

	public String contentFlushNote(String renturnAdress,String flag,String cateID,String reserve){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("renturnAdress", renturnAdress));
		nvps.add(new BasicNameValuePair("flag", flag));
		nvps.add(new BasicNameValuePair("cateID", cateID));
		nvps.add(new BasicNameValuePair("reserve", reserve));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "contentFlushNote", RestUrlPath.RETURN_TYPE_JSON);
	}		
	
	public String queryRingBoxBillboardInfo(String nodeId,int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("nodeId",nodeId));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryRingBoxBillboardInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryRingBoxInfo(int boxId){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("boxId", String.valueOf(boxId)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryRingBoxInfo", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String queryContentBillboardInfoEx(String billboardType,int startNum,int maxNum){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("billboardType",billboardType));
		nvps.add(new BasicNameValuePair("startNum", String.valueOf(startNum)));
		nvps.add(new BasicNameValuePair("maxNum", String.valueOf(maxNum)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.CONTENT_URL, "queryContentBillboardInfoEx", RestUrlPath.RETURN_TYPE_JSON);
	}
}
