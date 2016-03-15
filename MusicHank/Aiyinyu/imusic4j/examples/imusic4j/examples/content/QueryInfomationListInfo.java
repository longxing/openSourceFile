package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryInfomationListInfo {

	/**获取信息列表
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		int parentId = 355535;
		System.out.println(c.queryInfomationListInfo(parentId));
	}

}
