package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryOuMeiBillboardInfo {

	/**获取欧美榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryOuMeiBillboardInfo(0, 5));
	}

}
