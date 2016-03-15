package imusic4j.examples.cloud;

import imusic4j.interfaces.MoonCloudMusic;

public class GetSongInfo {

	/**根据歌曲id获歌曲信息
	 * @param args
	 */
	public static void main(String[] args) {
		MoonCloudMusic m = new MoonCloudMusic();
		String songid = "809";
		System.out.println(m.getSongInfo(songid));
	}

}
