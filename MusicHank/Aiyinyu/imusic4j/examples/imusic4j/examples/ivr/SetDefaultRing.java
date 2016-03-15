package imusic4j.examples.ivr;

import imusic4j.interfaces.Ivr;

public class SetDefaultRing {

	/**设置默认铃音
	 * @param args
	 */
	public static void main(String[] args) {
		Ivr i = new Ivr();
		String mdn = "15348911537";
		String ringID = "810034570040"; 
		System.out.println(i.setDefaultRing(mdn, ringID));
	}

}
