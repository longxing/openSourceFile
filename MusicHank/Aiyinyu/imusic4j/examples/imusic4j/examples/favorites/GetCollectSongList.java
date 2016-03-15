package imusic4j.examples.favorites;

import imusic4j.interfaces.Favorites;

public class GetCollectSongList {

	/**获取收藏夹歌曲列表
	 * @param args
	 */
	public static void main(String[] args) {
		Favorites f = new Favorites();
		String mdn = "135803****";
		int startNum = 1;
		int maxNum =1;
		System.out.println(f.getCollectSongList(mdn, startNum, maxNum));
	}

}
