package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class QueryRing {

	/**查询个人铃音库
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15360040934";
		int startnum = 0;
		int maxnum = 1;
		System.out.println(c.queryRing(mdn, startnum, maxnum));
	}

}
