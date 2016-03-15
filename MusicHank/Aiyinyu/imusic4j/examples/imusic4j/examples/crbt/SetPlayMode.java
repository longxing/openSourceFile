package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class SetPlayMode {

	/**设置铃音播放模式
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15360040934";
	    int mdnType = 0; 
	    int playMode = 3;
		System.out.println(c.setPlayMode(mdn, mdnType, playMode));
	}

}
