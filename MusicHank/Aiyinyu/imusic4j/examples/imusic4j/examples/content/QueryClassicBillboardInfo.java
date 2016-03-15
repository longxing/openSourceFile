package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryClassicBillboardInfo {

	/**获取经典榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryClassicBillboardInfo(0, 5));
	}

}
