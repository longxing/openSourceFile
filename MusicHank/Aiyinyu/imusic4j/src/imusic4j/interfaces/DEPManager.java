package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class DEPManager {
	
	public String  findPhoneNumberByIMSI(String imsi) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("imsi", imsi));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.DEP_URL, "findphonenumberbyimsi", RestUrlPath.RETURN_TYPE_XML);
	}
}
