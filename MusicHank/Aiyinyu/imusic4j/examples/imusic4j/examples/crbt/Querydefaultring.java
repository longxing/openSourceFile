package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class Querydefaultring {

	/**查询默认铃音
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		System.out.println(c.queryDafaultRing(mdn));
	}

}
