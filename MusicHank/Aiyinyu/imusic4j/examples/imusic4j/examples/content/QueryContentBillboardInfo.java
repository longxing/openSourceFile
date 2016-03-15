package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryContentBillboardInfo {

	/**榜单内容查询
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		String billboardType = "355535";
		int startNum = 0;
		int maxNum = 5;
		System.out.println(c.queryContentBillboardInfo(billboardType, startNum, maxNum));
	}

}
