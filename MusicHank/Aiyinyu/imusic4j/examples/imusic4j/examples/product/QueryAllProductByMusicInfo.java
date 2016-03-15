package imusic4j.examples.product;

import imusic4j.interfaces.Product;

public class QueryAllProductByMusicInfo {

	/**查询产品全部信息
	 * @param args
	 */
	public static void main(String[] args) {
		Product p = new Product();
		String actorName = "刀郎";
		String songName = "冲动的惩罚";
		int contentID = 34594;
		String CPID = "cpid";
		System.out.println(p.queryAllProductByMusicInfo(actorName, songName, contentID, CPID));
	}

}
