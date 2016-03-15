package imusic4j.examples.crbt;

import imusic4j.interfaces.Crbt;

public class QueryPlayMode {

	/**查询铃音播放模式
	 * @param args
	 */
	public static void main(String[] args) {
		Crbt c = new Crbt();
		String mdn = "15360040934";
		String mdnType = "0"; 
		System.out.println(c.queryPlayMode(mdn, mdnType));
	}

}
