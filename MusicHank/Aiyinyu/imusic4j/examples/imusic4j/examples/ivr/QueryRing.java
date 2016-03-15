package imusic4j.examples.ivr;

import imusic4j.interfaces.Ivr;

public class QueryRing {

	/**查询个人铃音库
	 * @param args
	 */
	public static void main(String[] args) {
		Ivr i = new Ivr();
		String mdn = "15348911537";
		int startnum = 0;
		int maxnum = 3;
		System.out.println(i.queryRing(mdn, startnum, maxnum));
	}

}
