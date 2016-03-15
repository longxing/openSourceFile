package imusic4j.examples.cloud;

import imusic4j.interfaces.MoonCloudMusic;

public class GetAlbumMusicList {

	/**获取专辑歌曲列表
	 * @param args
	 */
	public static void main(String[] args) {
		MoonCloudMusic m = new MoonCloudMusic();
		String mdn = "1533174****";
		String albumid = "706";//8681,976
		int startindex = 1;
		int pagesize = 10;
		System.out.println(m.getAlbumMusicList(mdn, albumid, startindex, pagesize));
	}

}
