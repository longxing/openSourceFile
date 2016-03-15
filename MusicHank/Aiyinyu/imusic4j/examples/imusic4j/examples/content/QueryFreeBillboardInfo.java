package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryFreeBillboardInfo {

	/**获取免费榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryFreeBillboardInfo(0, 5));
	}

}
