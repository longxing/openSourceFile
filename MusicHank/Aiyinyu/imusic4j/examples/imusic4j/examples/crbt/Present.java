package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class Present {

	/**赠送彩铃
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		String tomdn = "13308991574";
		String randomkey = "556282";
		String ringID = "810030205270";
		System.out.println(c.present(mdn, tomdn, randomkey, ringID));
	}

}
