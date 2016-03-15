package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryNewSongBillboardInfo {

	/**获取新歌榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryNewSongBillboardInfo(0, 5));
	}

}
