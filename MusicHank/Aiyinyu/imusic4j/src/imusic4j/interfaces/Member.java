package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Member {
	
	public String addAiYueBi(String mdn, int type) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("type", String.valueOf(type)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MEMBER_URL, "addaiyuebi", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String getCountOfMembersMenoy(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MEMBER_URL, "getcountofmembersmenoy", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String mumberFindByPhone(String mdn, String randomkey){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("randomkey", randomkey));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MEMBER_URL, "mumberFindByPhone", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String mumberFindByPhoneWithOutRandomKey(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MEMBER_URL, "mumberfindbyphonewithoutrandokey", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String mumberOperation(String mdn, String randomkey,int phoneType, int grade, String oprType){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("randomkey", randomkey));
		nvps.add(new BasicNameValuePair("phoneType", String.valueOf(phoneType)));
		nvps.add(new BasicNameValuePair("grade", String.valueOf(grade)));
		nvps.add(new BasicNameValuePair("oprType", oprType));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MEMBER_URL, "mumberoperation", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String mumberOperationWithOutRandomKey(String mdn,int phoneType, int grade, String oprType){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("phoneType", String.valueOf(phoneType)));
		nvps.add(new BasicNameValuePair("grade", String.valueOf(grade)));
		nvps.add(new BasicNameValuePair("oprType", oprType));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MEMBER_URL, "mumberoperationwithoutrandomkey", RestUrlPath.RETURN_TYPE_XML);
	}
	
	public String mumberSendSms(String mdn){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MEMBER_URL, "mumbersendsms", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String order(String mdn, String ringId, int price){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ringId", ringId));
		nvps.add(new BasicNameValuePair("price", String.valueOf(price)));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MEMBER_URL, "order", RestUrlPath.RETURN_TYPE_JSON);
	}
	
	public String pay(String mdn, String code, String productName,int price, String shopName){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("code", code));
		nvps.add(new BasicNameValuePair("productName", productName));
		nvps.add(new BasicNameValuePair("price", String.valueOf(price)));
		nvps.add(new BasicNameValuePair("shopName", shopName));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MEMBER_URL, "pay", RestUrlPath.RETURN_TYPE_JSON);
	}
}
