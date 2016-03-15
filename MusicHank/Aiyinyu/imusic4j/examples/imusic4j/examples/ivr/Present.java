package imusic4j.examples.ivr;

import imusic4j.interfaces.Ivr;

public class Present {

	/**赠送彩铃
	 * @param args
	 */
	public static void main(String[] args) {
		Ivr i = new Ivr();
		String mdn = "15348911537";
		String tomdn = "13308991574";
		String ringID = "810030205270";
		System.out.println(i.present(mdn, tomdn,ringID));
	}

}
