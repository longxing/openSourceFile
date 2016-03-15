package imusic4j.examples.actoralbum;

import imusic4j.interfaces.ActorAlbumInfo;

public class FindMusicsByAlbumId {

	/**专辑歌曲列表查询
	 * @param args
	 */
	public static void main(String[] args) {
		ActorAlbumInfo a = new ActorAlbumInfo();
		String albumId = "8681";
		String startNum = "1";
		String maxNum = "5";
		System.out.println(a.findMusicsByAlbumId(albumId, startNum, maxNum));
	}

}
