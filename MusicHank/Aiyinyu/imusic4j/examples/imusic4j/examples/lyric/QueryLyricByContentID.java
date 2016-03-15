package imusic4j.examples.lyric;

import imusic4j.interfaces.Lyric;

public class QueryLyricByContentID {

	/**根据内容id查询歌词
	 * @param args
	 */
	public static void main(String[] args) {
		Lyric l = new Lyric();
		String contentID = "197673";
		String type = "lrc";
		System.out.println(l.queryLyricByContentID(contentID, type));
	}

}
