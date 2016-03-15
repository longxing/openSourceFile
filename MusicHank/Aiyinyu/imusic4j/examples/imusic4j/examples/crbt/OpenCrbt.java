package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class OpenCrbt {

	/**开通彩铃
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		String randomkey = "644278";
		System.out.println(c.openCrbt(mdn, randomkey));
	}

}
