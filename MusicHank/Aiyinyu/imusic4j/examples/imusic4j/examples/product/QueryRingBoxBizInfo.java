package imusic4j.examples.product;

import imusic4j.interfaces.Product;

public class QueryRingBoxBizInfo {

	/**音乐盒中产品查询
	 * @param args
	 */
	public static void main(String[] args) {
		Product p = new Product();
		String boxFeeId = "810099993301";
		System.out.println(p.queryRingBoxBizInfo(boxFeeId));
	}

}
