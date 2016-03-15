package imusic4j.examples.actoralbum;

import imusic4j.interfaces.ActorAlbumInfo;

public class FindMusicOfActor {

	/**歌手歌曲列表查询
	 * @param args
	 */
	public static void main(String[] args) {
		ActorAlbumInfo a = new ActorAlbumInfo();
		String actorID = "1260";
		String startNum = "1";
		String maxNum = "5";
		System.out.println(a.findMusicOfActor(actorID, startNum, maxNum));
	}

}
