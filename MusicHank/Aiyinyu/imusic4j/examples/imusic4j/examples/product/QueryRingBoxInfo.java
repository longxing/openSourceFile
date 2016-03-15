package imusic4j.examples.product;

import imusic4j.interfaces.Product;

public class QueryRingBoxInfo {

	/**铃音盒详情查询
	 * @param args
	 */
	public static void main(String[] args) {
		Product p = new Product();
		String boxFeeId = "810099995105";
		System.out.println(p.queryRingBoxInfo(boxFeeId));
	}

}
