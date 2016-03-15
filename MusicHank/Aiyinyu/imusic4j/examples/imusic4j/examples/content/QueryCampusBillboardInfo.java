package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryCampusBillboardInfo {

	/**获取校园榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryCampusBillboardInfo(0, 5));
	}

}
