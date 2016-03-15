package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryBillboardListInfo {

	/**
	 * 获取榜单列表
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryBillboardListInfo());
	}

}
