package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class OrderByImsi {

	/**通过imsi订购彩铃音乐盒
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		String ringID = "810037200300";
		String type = "1";
		String imsi = "460036221166226";
		int setDefaultCrbt = 1; 
		System.out.println(c.orderByImsi(mdn, ringID, type, imsi, setDefaultCrbt));
	}

}
