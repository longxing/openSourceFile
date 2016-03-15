package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryRingBoxBillboardInfo {

	/**获取音乐盒榜单信息
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		String nodeId = "287898";
		int startNum = 0;
		int maxNum = 5;
		System.out.println(c.queryRingBoxBillboardInfo(nodeId, startNum, maxNum));
	}

}
