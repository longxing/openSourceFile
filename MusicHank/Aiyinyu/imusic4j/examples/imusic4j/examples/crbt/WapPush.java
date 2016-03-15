package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class WapPush {

	/**音乐下载
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
	    String randomkey = "136443"; 
	    String ringID = "107800133800";
		System.out.println(c.wapPush(mdn, randomkey, ringID));
	}

}
