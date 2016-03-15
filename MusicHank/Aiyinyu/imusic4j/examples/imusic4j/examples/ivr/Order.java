package imusic4j.examples.ivr;

import imusic4j.interfaces.Ivr;

public class Order {

	/**订购彩铃音乐盒
	 * @param args
	 */
	public static void main(String[] args) {
		Ivr i = new Ivr();
		String mdn = "15348911537";
		String ringID = "810034570040";
		String type = "1";
		int setDefaultCrbt = 1; 
		System.out.println(i.order(mdn, ringID, type,setDefaultCrbt));
	}

}
