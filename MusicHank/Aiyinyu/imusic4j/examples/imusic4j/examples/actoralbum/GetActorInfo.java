package imusic4j.examples.actoralbum;

import imusic4j.interfaces.ActorAlbumInfo;

public class GetActorInfo {

	/**歌手信息查询
	 * @param args
	 */
	public static void main(String[] args) {
		ActorAlbumInfo a = new ActorAlbumInfo();
		String id = "0";//歌手id:638  彩铃id:810030204190 振铃id:100200433004 全曲id:110200003600 内容id:31353
		int idtype = 0;
		String actorname = "刘德华";
		System.out.println(a.getActorInfo(id, idtype, actorname));
	}

}
