package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class Ringtonedownload {
	/**
	 * 包月用户下载铃音接口
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15348911537";
		String ringID = "107800133800";
		System.out.println(c.ringtonedownload(mdn, ringID));
	}
}
