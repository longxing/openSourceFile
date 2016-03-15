package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryPopularBillboardInfo {

	/**获取流行榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryPopularBillboardInfo(0, 5));
	}

}
