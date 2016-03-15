package imusic4j.examples.actoralbum;

import imusic4j.interfaces.ActorAlbumInfo;

public class FindAlbumBySong {

	/**专辑信息查询
	 * @param args
	 */
	public static void main(String[] args) {
		ActorAlbumInfo a = new ActorAlbumInfo();
		String id = "2573912";//彩铃id:810030202588 振铃id:100200492304 全曲id:100500000700 内容id:983074
		int idtype = 0;
		String songname = "jay";
		System.out.println(a.findAlbumBySong(id, idtype, songname));
	}

}
