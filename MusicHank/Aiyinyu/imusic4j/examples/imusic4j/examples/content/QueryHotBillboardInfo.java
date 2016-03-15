package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryHotBillboardInfo {

	/**获取热榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryHotBillboardInfo(0, 5));
	}

}
