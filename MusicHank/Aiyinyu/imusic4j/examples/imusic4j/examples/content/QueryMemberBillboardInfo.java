package imusic4j.examples.content;

import imusic4j.interfaces.Content;

public class QueryMemberBillboardInfo {

	/**获取会员专区榜
	 * @param args
	 */
	public static void main(String[] args) {
		Content c = new Content();
		System.out.println(c.queryMemberBillboardInfo(0, 5));
	}

}
