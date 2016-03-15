package imusic4j.examples.lyric;

import imusic4j.interfaces.Lyric;

public class QueryLyricByResourceID {

	/**根据资源id获取歌词
	 * @param args
	 */
	public static void main(String[] args) {
		Lyric l = new Lyric();
		String resourceID = "1165001495";
		String type = "lrc";
		System.out.println(l.queryLyricByResourceID(resourceID, type));
	}

}
