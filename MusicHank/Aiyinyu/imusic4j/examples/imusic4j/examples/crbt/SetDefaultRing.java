package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class SetDefaultRing {

	/**设置默认铃音
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		String ringID = "810034570040"; 
		System.out.println(c.setDefaultRing(mdn, ringID));
	}

}
