package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryFunnyBillboardInfo {

	/**获取搞笑榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryFunnyBillboardInfo(0, 5));
	}

}
