package imusic4j.examples.ivr;

import imusic4j.interfaces.Ivr;

public class WapPush {

	/**音乐下载
	 * @param args
	 */
	public static void main(String[] args) {
		Ivr i = new Ivr();
		String mdn = "15348911537";
	    String ringID = "107800133800";
		System.out.println(i.wapPush(mdn,ringID));
	}

}
