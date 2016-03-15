package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryWeekBillboardInfo {

	/**获取周榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryWeekBillboardInfo(0, 5));
	}

}
