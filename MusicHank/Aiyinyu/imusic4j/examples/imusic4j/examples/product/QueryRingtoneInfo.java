package imusic4j.examples.product;

import imusic4j.interfaces.Product;

public class QueryRingtoneInfo {

	/**振铃详情查询
	 * @param args
	 */
	public static void main(String[] args) {
		Product p = new Product();
		String mdmcMusicId = "1078001338";
		System.out.println(p.queryRingtoneInfo(mdmcMusicId));
	}

}
