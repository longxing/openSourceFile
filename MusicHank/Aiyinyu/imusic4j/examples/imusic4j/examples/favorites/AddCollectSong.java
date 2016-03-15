package imusic4j.examples.favorites;

import imusic4j.interfaces.Favorites;

public class AddCollectSong {

	/**往收藏夹添加歌曲
	 * @param args
	 */
	public static void main(String[] args) {
		Favorites f = new Favorites();
		String mdn = "13580326110";
		String mdmcMusicId = "810048565458";
		String singer = "李克勤";
		String song = "红日";
		System.out.println(f.addCollectSong(mdn, mdmcMusicId, singer, song));
	}

}
