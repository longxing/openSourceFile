package imusic4j.examples.ivr;

import imusic4j.interfaces.Ivr;

public class QueryDefaultring {

	/**查询默认铃音
	 * @param args
	 */
	public static void main(String[] args) {
		Ivr i = new Ivr();
		String mdn = "15348911537";
		System.out.println(i.queryDafaultRing(mdn));
	}

}
