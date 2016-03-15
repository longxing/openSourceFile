package imusic4j.examples.product;

import imusic4j.interfaces.Product;

public class QueryFullTrackInfo {

	/**全曲详情查询
	 * @param args
	 */
	public static void main(String[] args) {
		Product p = new Product();
		String mdmcMusicId = "1194000055";
		System.out.println(p.queryFullTrackInfo(mdmcMusicId));
	}

}
