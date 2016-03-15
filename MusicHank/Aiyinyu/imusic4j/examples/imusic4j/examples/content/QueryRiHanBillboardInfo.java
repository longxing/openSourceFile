package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryRiHanBillboardInfo {

	/**获取日韩榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryRiHanBillboardInfo(0, 5));
	}

}
