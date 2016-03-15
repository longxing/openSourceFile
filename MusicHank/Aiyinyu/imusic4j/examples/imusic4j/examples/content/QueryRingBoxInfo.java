package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryRingBoxInfo {

	/**获取音乐盒信息
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		int boxId = 1102;
		System.out.println(c.queryRingBoxInfo(boxId));
	}

}
