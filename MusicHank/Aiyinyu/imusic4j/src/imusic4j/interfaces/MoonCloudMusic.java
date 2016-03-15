package imusic4j.interfaces;

import imusic4j.RestUrlPath;
import imusic4j.util.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class MoonCloudMusic {

	public String getAlbumMusicList(String mdn, String albumid, int startindex,
			int pagesize) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("albumid", albumid));
		nvps.add(new BasicNameValuePair("startindex", String
				.valueOf(startindex)));
		nvps.add(new BasicNameValuePair("pagesize", String.valueOf(pagesize)));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getalbummusiclist", RestUrlPath.RETURN_TYPE_JSON);
	}

	public String getDefaultAlbum(String mdn) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getdefaultalbum", RestUrlPath.RETURN_TYPE_JSON);
	}

	public String getSongInfo(String songid) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("songid", songid));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getsonginfo", RestUrlPath.RETURN_TYPE_JSON);
	}

	public String getUserAlbumList(String mdn, String ordertype) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		nvps.add(new BasicNameValuePair("ordertype", ordertype));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getuseralbumlist", RestUrlPath.RETURN_TYPE_JSON);
	}

	public String getUserCapacity(String mdn) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getusercapacity", RestUrlPath.RETURN_TYPE_JSON);
	}

	public String getuserextendByPhone(String mdn) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mdn", mdn));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getuserextendbyphone", RestUrlPath.RETURN_TYPE_JSON);
	}

	public String getSongListOfUsersRecent(int userid, String songstate,
			int startindex, int pagesize, int origintype) {

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userid", userid + ""));
		nvps.add(new BasicNameValuePair("songstate", songstate));
		nvps.add(new BasicNameValuePair("startindex", startindex + ""));
		nvps.add(new BasicNameValuePair("pagesize", pagesize + ""));
		nvps.add(new BasicNameValuePair("origintype", origintype + ""));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getsonglistofusersrecent", RestUrlPath.RETURN_TYPE_JSON);

	}

	public String addPlayGroupFullInfo(int userid, java.lang.String groupname,
			java.lang.String picaddress, java.lang.String description,
			java.lang.Integer state) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userid", userid + ""));
		nvps.add(new BasicNameValuePair("groupname", groupname));
		nvps.add(new BasicNameValuePair("picaddress", picaddress + ""));
		nvps.add(new BasicNameValuePair("description", description + ""));
		nvps.add(new BasicNameValuePair("state", state + ""));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"addplaygroupfullinfo", RestUrlPath.RETURN_TYPE_JSON);
	}

	/**
	 * 添加新的歌曲
	 * 
	 * @param songadress
	 * @param userid
	 * @param songname
	 * @param singername
	 * @param songqukuid
	 * @param songchannel
	 * @param SongState
	 * @param songsize
	 * @param oldalbumname
	 * @return
	 */

	public 	String addQuKuSong(java.lang.String songadress, int userid,
			java.lang.String songname, java.lang.String singername,
			java.lang.String songqukuid, int songchannel,
			java.lang.String SongState, float songsize,
			java.lang.String oldalbumname) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("songadress", songadress + ""));
		nvps.add(new BasicNameValuePair("userid", userid + ""));
		nvps.add(new BasicNameValuePair("songname", songname + ""));
		nvps.add(new BasicNameValuePair("singername", singername + ""));
		nvps.add(new BasicNameValuePair("songqukuid", songqukuid + ""));

		nvps.add(new BasicNameValuePair("songchannel", songchannel + ""));
		nvps.add(new BasicNameValuePair("songstate", SongState + ""));
		nvps.add(new BasicNameValuePair("songsize", songsize + ""));
		nvps.add(new BasicNameValuePair("oldalbumname", oldalbumname + ""));
		return HttpUtils.httpPostTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"addqukusong", RestUrlPath.RETURN_TYPE_JSON);
	}

	/**
	 * 修改专辑信息
	 * 
	 * 
	 * @param groupid
	 * @param userid
	 * @param newgroupname
	 * @param newdescription
	 * @param newgrouppic
	 * @return
	 */

	public String updatePlayGroupName(int groupid, java.lang.String userid,
			java.lang.String newgroupname, java.lang.String newdescription,
			java.lang.String newgrouppic) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("groupid", groupid + ""));
		nvps.add(new BasicNameValuePair("userid", userid + ""));
		nvps.add(new BasicNameValuePair("newgroupname", newgroupname + ""));
		nvps.add(new BasicNameValuePair("newdescription", newdescription + ""));
		nvps.add(new BasicNameValuePair("newgrouppic", newgrouppic + ""));

		return HttpUtils.httpPostTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"updateplaygroupname", RestUrlPath.RETURN_TYPE_JSON);
	}

	/**
	 * 修改歌曲信息
	 * 
	 * 
	 * 
	 * @param SongId
	 * @param SongName
	 * @param SingerName
	 * @param SongState
	 * @param SongChannel
	 * @return
	 */

	public	String updateSongInfo(int SongId, java.lang.String SongName,
			java.lang.String SingerName, java.lang.Integer SongState,
			java.lang.Integer SongChannel) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("songid", SongId + ""));
		nvps.add(new BasicNameValuePair("songname", SongName + ""));
		nvps.add(new BasicNameValuePair("singername", SingerName + ""));
		nvps.add(new BasicNameValuePair("songstate", SongState + ""));
		nvps.add(new BasicNameValuePair("songchannel", SongChannel + ""));

		return HttpUtils.httpPostTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"updatesonginfo", RestUrlPath.RETURN_TYPE_JSON);
	}

	/**
	 * 删除专辑
	 * 
	 * 
	 * @param GroupId
	 * 
	 * @return
	 */

	public String deletePlayGroup(int GroupId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("groupid", GroupId + ""));

		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"deletealbum", RestUrlPath.RETURN_TYPE_JSON);
	}

	/**
	 * 删除歌曲
	 * 
	 * 
	 * @param SongId
	 * @param userId
	 * @return
	 */

	public String deleteSong(int SongId, java.lang.String userId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("songid", SongId + ""));
		nvps.add(new BasicNameValuePair("userid", userId + ""));

		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"deletesong", RestUrlPath.RETURN_TYPE_JSON);
	}

	/**
	 *获取用户下载记录列表
	 * 
	 * 
	 * @param SongId
	 * @param userId
	 * @return
	 */

	public String downloadRecords(String mobile, int startindex, int pagesize) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", mobile + ""));
		nvps.add(new BasicNameValuePair("startindex", startindex + ""));

		nvps.add(new BasicNameValuePair("pagesize", pagesize + ""));
		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getuserdownloadlistsponse", RestUrlPath.RETURN_TYPE_JSON);
	}

	/**
	 *获取歌曲对象
	 * 
	 * 
	 * songName -歌曲名称（必填） siner -歌手名称（必填） uploadKey -用于pc客户端本地上传判断 userId –用户ID
	 * （必填） orgin - 歌曲来源类型: 1云音乐记录的用户上传歌曲 2云音乐记录的曲库歌曲
	 * 
	 * @param songName
	 * @param siner
	 * @param uploadKey
	 * @param userId
	 * @param orgin
	 * @return
	 */
	public	String getSongByKey(java.lang.String songName, java.lang.String siner,
			java.lang.String uploadKey, java.lang.String userId,
			java.lang.String orgin) {

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("songname", songName + ""));
		nvps.add(new BasicNameValuePair("singer", siner + ""));

		nvps.add(new BasicNameValuePair("uploadkey", uploadKey + ""));
		nvps.add(new BasicNameValuePair("userid", userId + ""));

		nvps.add(new BasicNameValuePair("orgin", orgin + ""));

		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getsongbykey", RestUrlPath.RETURN_TYPE_JSON);

	}

	/**
	 * 添加一条歌曲到专辑中 SongId - 歌曲id GroupId - 专辑的ID
	 * 
	 * @param SongId
	 * @param GroupId
	 * @param firstUserId
	 * @param ownIdId
	 * @return
	 */
	public	String addSongToPlayGroup(int SongId, int GroupId, int firstUserId,
			int ownIdId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("songId", SongId + ""));
		nvps.add(new BasicNameValuePair("groupId", GroupId + ""));

		nvps.add(new BasicNameValuePair("firstUserId", firstUserId + ""));
		nvps.add(new BasicNameValuePair("ownIdId", ownIdId + ""));

		return HttpUtils.httpPostTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"addsongtoplaygroup", RestUrlPath.RETURN_TYPE_JSON);

	}

	/**
	 * 非曲库上传 存储歌曲信息保存到对应用户的歌曲信息表中
	 * 
	 * UserId - 用户ID SongName - 歌曲名称 最大32个字符 SingerName - 歌手名称 最大32个字符 SongLrc -
	 * 歌词内容 SongChannel - 歌曲渠道标识：1PC客户端，2Web，3Wap，5全点客户端记录的用户上传歌曲 6全点客户端记录的曲库歌曲
	 * SongState - 歌曲状态：1全公开 2仅好友 3删除 不填(null)默认为1 ExtendName - 歌曲扩展名称:wma、mp3
	 * songSize - 歌曲大小 uploadKey - pc匹配上传标识 songHost - 歌曲地址前缀
	 * 
	 * @param songaddress
	 * @param UserId
	 * @param SongName
	 * @param SingerName
	 * @param SongLrc
	 * @param SongChannel
	 * @param SongState
	 * @param ExtendName
	 * @param songSize
	 * @param uploadKey
	 * @param songHost
	 * @param oldAlbumName
	 * @return
	 */
	public String addUploadSong(java.lang.String songaddress, int UserId,
			java.lang.String SongName, java.lang.String SingerName,
			java.lang.String SongLrc, int SongChannel,
			java.lang.String SongState, java.lang.String ExtendName,
			float songSize, java.lang.String uploadKey,
			java.lang.String songHost, java.lang.String oldAlbumName) {

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("songaddress", songaddress + ""));
		nvps.add(new BasicNameValuePair("userid", UserId + ""));

		nvps.add(new BasicNameValuePair("songname", SongName + ""));
		nvps.add(new BasicNameValuePair("singername", SingerName + ""));

		nvps.add(new BasicNameValuePair("songlrc", SongLrc + ""));
		nvps.add(new BasicNameValuePair("songchannel", SongChannel + ""));

		nvps.add(new BasicNameValuePair("songstate", SongState + ""));
		nvps.add(new BasicNameValuePair("extendname", ExtendName + ""));

		nvps.add(new BasicNameValuePair("songsize", songSize + ""));
		nvps.add(new BasicNameValuePair("uploadkey", uploadKey + ""));

		nvps.add(new BasicNameValuePair("songhost", songHost + ""));
		nvps.add(new BasicNameValuePair("oldalbumname", oldAlbumName + ""));

		return HttpUtils.httpPostTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"adduploadsong", RestUrlPath.RETURN_TYPE_JSON);

	}

	/**
	 * 获取用户可用的专辑数量和歌曲数量
	 * 
	 * @param userid
	 * @return
	 */
	public	String getusersalbumsongcount(java.lang.String userid) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userid", userid + ""));

		return HttpUtils.httpGetTool(nvps, RestUrlPath.MOONCLOUD_URL,
				"getusersalbumsongcount", RestUrlPath.RETURN_TYPE_JSON);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		MoonCloudMusic tool = new MoonCloudMusic();
		System.out.println("getuserextendByPhone ------------");
		System.out.println(tool.getuserextendByPhone("18908509365"));
		/*
			String orgin = "";
		String uploadKey = "";
		String songName = "love";
		String singer = "萧亚轩";
		String userId = "87";
		System.out.println("getSongByKey ------------");
		System.out.println(tool.getSongByKey(songName, singer, uploadKey,
				userId, orgin));
		
		System.out.println("getSongListOfUsersRecent ------------");
		System.out.println(tool.getSongListOfUsersRecent(87, "4", 1, 10, 1));
		
		System.out.println("addPlayGroupFullInfo ------------");
		

		System.out.println(tool.addPlayGroupFullInfo(87, "专辑名称12", "", "", new Integer(1)));
		
		System.out.println("addQuKuSong ---eee---------");
		//System.out.println(tool.addQuKuSong("/ameapp.mp3", 87, "相爱的人都等在这地", "方黄雅莉", "1437000142", 4, "", (float)0, ""));
		System.out.println(URLEncoder.encode("王菲+陈奕迅", "utf8"));
		System.out.println(tool.addQuKuSong("/res/1436/mp3/00/00/40/1436000040000800.mp3", 87, "因为爱情","王菲+陈奕迅","1437000142", 4, "", (float)3390.6, ""));
		
		System.out.println("updatePlayGroupName ------------");
		System.out.println(tool.updatePlayGroupName(232, "87", "专辑333", "", ""));
		
		
		
		System.out.println("updateSongInfo ------------");
		System.out.println(tool.updateSongInfo(401, "love", "萧亚轩", 1, 1));
		
		
		System.out.println("deletePlayGroup ------------");*/
		
	
		
		System.out.println("downloadRecords ------------");
		System.out.println(tool.downloadRecords("18989938777", 1, 10));
		/*	
	System.out.println("addSongToPlayGroup ------------");
		System.out.println(tool.addSongToPlayGroup(401,232 , 87, 87));
		
		
		System.out.println("addUploadSong ------------");
		System.out.println(tool.addUploadSong("/aaa.mp3", 87, "love", "萧亚轩", "", 6, "", "mp3", (float)100, "aa", "http://118100.cn", "test"));
	
		
		System.out.println("getusersalbumsongcount ------------");
		System.out.println(tool.getusersalbumsongcount("87"));
		
		
		System.out.println(tool.deletePlayGroup(232));
		
		System.out.println("deleteSong ------------");
		System.out.println(tool.deleteSong(403,"87"));
		
		 
		
		System.out.println(tool.getSongInfo("447"));
		*/
	}

}
