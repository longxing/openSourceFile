package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class Order {

	/**订购彩铃音乐盒
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		String ringID = "810037200344";
		String type = "1";
		String randomkey = "426863";
		int setDefaultCrbt = 1; 
		System.out.println(c.order(mdn, ringID, type, randomkey, setDefaultCrbt));
	}

}
