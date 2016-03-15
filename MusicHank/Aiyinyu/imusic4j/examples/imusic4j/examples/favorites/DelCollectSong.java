package imusic4j.examples.favorites;

import imusic4j.interfaces.Favorites;

public class DelCollectSong {

	/**删除收藏列表的歌曲
	 * @param args
	 */
	public static void main(String[] args) {
		Favorites f = new Favorites();
		String mdn = "1358032****";
		String mdmcMusicId = "810098798789";
		System.out.println(f.delCollectSong(mdn, mdmcMusicId));
	}

}
