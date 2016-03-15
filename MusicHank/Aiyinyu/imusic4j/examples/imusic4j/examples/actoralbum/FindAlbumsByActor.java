package imusic4j.examples.actoralbum;

import imusic4j.interfaces.ActorAlbumInfo;

public class FindAlbumsByActor {

	/**歌手专辑列表查询
	 * @param args
	 */
	public static void main(String[] args) {
		ActorAlbumInfo a = new ActorAlbumInfo();
		String actorId = "296";
		String startNum = "1";
		String maxNum = "5";
		String order = "id";
		System.out.println(a.findAlbumsByActor(actorId, startNum, maxNum, order));
	}

}
